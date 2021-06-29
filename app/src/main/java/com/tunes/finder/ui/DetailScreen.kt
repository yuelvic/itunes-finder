package com.tunes.finder.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.tunes.finder.domain.model.Media
import com.tunes.finder.presentation.SearchViewModel

@Composable
fun DetailScreen(searchViewModel: SearchViewModel) {
    val media = searchViewModel.searchResultData.observeAsState(initial = Media(
        resultCount = 0,
        result = listOf()
    ))
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(16.dp)
    ) {
        Image(
            painter = rememberCoilPainter(request = media.value.result[0].artworkUrl),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )
        Text(text = media.value.result[0].trackName, style = typography.h6)
        Text(text = media.value.result[0].artistName, style = typography.caption)
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    
}