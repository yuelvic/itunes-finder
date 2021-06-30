package com.tunes.finder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.tunes.finder.presentation.SearchViewModel
import com.tunes.finder.ui.ui.theme.ITunesFinderTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITunesFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()) {
                        OpenTime(searchViewModel = searchViewModel)
                        Search(searchViewModel = searchViewModel)
                        MediaList(searchViewModel = searchViewModel) {
                            startActivity(DetailActivity.newIntent(this@MainActivity, it))
                        }
                    }
                }
            }
        }
        searchViewModel.apply {
            getOpenTime()
            setOpenTime()
        }
    }
}

@Composable
fun OpenTime(searchViewModel: SearchViewModel) {
    val openTime = searchViewModel.openTimeData.observeAsState()
    if (openTime.value.isNullOrBlank()) return
    Text(text = "Last Opened: ${openTime.value!!}", style = typography.caption,
        modifier = Modifier.padding(top = 16.dp, start = 16.dp))
}

@Composable
fun Search(searchViewModel: SearchViewModel) {
    var text by rememberSaveable { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
        val localFocusManager = LocalFocusManager.current
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text(text = "Search") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    localFocusManager.clearFocus()
                    searchViewModel.search(text)
                }
            )
        )
    }
}