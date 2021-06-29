package com.tunes.finder.data.repository

import com.tunes.finder.data.remote.ApiService
import com.tunes.finder.domain.model.Media
import com.tunes.finder.domain.repository.SearchRepository

class SearchRepositoryImpl(private val apiService: ApiService) : SearchRepository {

    override suspend fun search(term: String): Media {
        return apiService.search(term = term)
    }

}