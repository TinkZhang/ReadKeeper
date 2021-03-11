package ink.iamt.readkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
                    Greeting(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: SearchViewModel) {
    val status: SearchStatus by viewModel.status.observeAsState(SearchStatus.STALE)
    val foundNumber: Int by viewModel.numberFound.observeAsState(0)
    Row {
        val label = when(status){
            SearchStatus.STALE -> "found $foundNumber books!!"
            SearchStatus.LOADING -> "loading"
            SearchStatus.LOADING_MORE -> TODO()
            SearchStatus.ERROR -> "something went wrong"
        }
        Text(text = "$label")
        Button(onClick = {
            viewModel.search("hello")
        }) {
            Text(text = "Search")
        }
    }
}