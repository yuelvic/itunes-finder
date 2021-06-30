package com.tunes.finder.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.tunes.finder.domain.model.Medium

@Composable
fun MediumListItem(
    medium: Medium,
    navigateToDetailView: (Long) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(Modifier.clickable { navigateToDetailView(medium.trackId) }) {
            AlbumImage(artworkUrl = medium.artworkUrl)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = medium.trackName, style = typography.h6)
                Text(text = medium.genre, style = typography.caption)
                Text(text = "₱${medium.trackPrice}.00", style = typography.caption)
            }
        }
    }
}

@Composable
private fun AlbumImage(artworkUrl: String) {
    Image(
        painter = rememberCoilPainter(request = artworkUrl),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(80.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}

@Preview
@Composable
fun PreviewMediumItem() {
    MediumListItem(medium = Medium(
        trackId = 1161445539,
        artistName = "Unique Salonga",
        trackName = "Sino",
        trackPrice = 35,
        trackLength = 160853,
        genre = "Rock",
        artworkUrl = "https://is2-ssl.mzstatic.com/image/thumb/Music113/v4/66/13/ed/6613ed6c-ed13-af24-a3ac-ffe37f512013/source/100x100bb.jpg"
    ), navigateToDetailView = {})
}