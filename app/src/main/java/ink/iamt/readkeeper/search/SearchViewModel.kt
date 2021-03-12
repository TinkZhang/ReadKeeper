package ink.iamt.readkeeper.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ink.iamt.readkeeper.network.model.OpenLibraryMapper
import ink.iamt.readkeeper.network.model.dto.OpenLibrarySearchDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TAG = "SearchViewModel"
class SearchViewModel(): ViewModel() {
    private val _status = MutableLiveData(SearchStatus.STALE)
    val status: LiveData<SearchStatus> = _status

    private val _numberFound = MutableLiveData(0)
    val numberFound: LiveData<Int> = _numberFound

    private val _books = MutableLiveData<List<SearchBook>>()
    val books: LiveData<List<SearchBook>> = _books

    val mapper = OpenLibraryMapper()

    fun search(keyword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(SearchStatus.LOADING)
            val response = SearchRepository.search(keyword, SearchEngine.OPEN_LIBRARY)
            if (response.isSuccessful) {
                Log.d(TAG, "${response.body().toString()}")
                val result = response.body() as? OpenLibrarySearchDto
                _numberFound.postValue(result?.numFound)
                _status.postValue(SearchStatus.STALE)
                _books.postValue(result?.docs?.let { mapper.mapToBooks(it) })
            } else {
                _status.postValue(SearchStatus.ERROR)
            }
        }
    }
}

enum class SearchStatus {
    STALE, LOADING, LOADING_MORE, ERROR
}