package com.tunes.finder.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import com.tunes.finder.domain.model.Media
import com.tunes.finder.presentation.SearchViewModel

@Composable
fun MediaList(
    searchViewModel: SearchViewModel,
    navigateToDetailView: (Long) -> Unit
) {
    val media = searchViewModel.searchResultData.observeAsState(Media(
        resultCount = 0,
        result = listOf()
    ))
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(
            items = media.value!!.result,
            itemContent = {
                MediumListItem(medium = it, navigateToDetailView = navigateToDetailView)
            }
        )
    }
}