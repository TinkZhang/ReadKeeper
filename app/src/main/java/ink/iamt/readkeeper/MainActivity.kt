package ink.iamt.readkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ink.iamt.readkeeper.search.SearchBook
import ink.iamt.readkeeper.search.SearchStatus
import ink.iamt.readkeeper.search.SearchViewModel
import ink.iamt.readkeeper.ui.theme.ReadKeeperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadKeeperTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val viewModel: SearchViewModel by viewModels()
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(getString(R.string.app_name))})},
                        content = { Greeting(viewModel) }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: SearchViewModel) {
    val status: SearchStatus by viewModel.status.observeAsState(SearchStatus.STALE)
    val foundNumber: Int by viewModel.numberFound.observeAsState(0)
    val books: List<SearchBook> by viewModel.books.observeAsState(listOf())

    Column {
        val label = when(status){
            SearchStatus.STALE -> if(books.isNotEmpty())
                {"found $foundNumber books!! and the first book is ${books[0].title}"}
            else {
                "press the button ->"
            }
            SearchStatus.LOADING -> "loading"
            SearchStatus.LOADING_MORE -> TODO()
            SearchStatus.ERROR -> "something went wrong"
        }
        var keyword by remember { mutableStateOf("")}
        val searchBoxEnabled = remember { mutableStateOf(false)}
        OutlinedTextField(
            value = keyword,
            label = { Text("Search Book") },
            trailingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")},
            onValueChange = { value ->
                keyword = value
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {viewModel.search(keyword)}),
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            viewModel.search(keyword)
        }) {
            Text(text = "Search")
        }
        LazyColumn {
            items(books){ book ->
                Text(book.title)
            }
        }
    }
}

@Preview
@Composable
fun HomeSearchPreview() {
    Greeting(viewModel = viewModel())
}