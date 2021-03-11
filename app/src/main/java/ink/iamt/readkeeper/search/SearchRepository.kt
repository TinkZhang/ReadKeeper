package ink.iamt.readkeeper.search

import ink.iamt.readkeeper.network.RetrofitInstance

object SearchRepository {
    suspend fun search(keyword: String, engine: SearchEngine) = when(engine) {
        SearchEngine.GOOGLE -> RetrofitInstance.googleBookApi.search(keyword)
        SearchEngine.OPEN_LIBRARY -> RetrofitInstance.openLibraryApi.search(keyword, 1)
    }
}

enum class SearchEngine {
    GOOGLE, OPEN_LIBRARY
}