package com.tunes.finder.domain.repository

import com.tunes.finder.domain.model.Media

interface SearchRepository {

    suspend fun search(term: String): Media

    suspend fun lookup(id: Long): Media

}