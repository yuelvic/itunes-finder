package com.tunes.finder.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tunes.finder.presentation.SearchViewModel
import com.tunes.finder.ui.ui.theme.ITunesFinderTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : ComponentActivity() {

    private val trackId : Long by lazy {
        intent.getLongExtra(TRACK_ID, 0)
    }

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITunesFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DetailScreen(searchViewModel = searchViewModel)
                }
            }
        }
        searchViewModel.lookup(trackId)
    }

    companion object {
        private const val TRACK_ID = "track_id"
        fun newIntent(context: Context, id: Long) = Intent(context, DetailActivity::class.java).apply {
            putExtra(TRACK_ID, id)
        }
    }

}