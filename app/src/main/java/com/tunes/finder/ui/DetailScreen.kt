package com.tunes.finder.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
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
import com.tunes.finder.utils.TimeUtil

@Composable
fun DetailScreen(searchViewModel: SearchViewModel) {
    val media = searchViewModel.searchResultData.observeAsState(initial = Media(
        resultCount = 0,
        result = listOf()
    ))
    if (media.value.resultCount <= 0) return
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
        ) {
            Image(
                painter = rememberCoilPainter(request = media.value.result.first().artworkUrl),
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = media.value.result.first().trackName, style = typography.h6)
                Text(text = media.value.result.first().artistName, style = typography.caption)
                Text(
                    text = TimeUtil.toTrackLength(media.value.result.first().trackLength),
                    style = typography.caption
                )
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onClick = {  }
        ) {
            Text(text = "Buy ₱${media.value.result.first().trackPrice}.00")
        }
    }
}