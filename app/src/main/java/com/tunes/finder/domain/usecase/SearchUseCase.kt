package com.tunes.finder.domain.usecase

import com.tunes.finder.domain.model.Media
import com.tunes.finder.domain.repository.SearchRepository
import com.tunes.finder.domain.usecase.base.UseCase

class SearchUseCase constructor(
    private val searchRepository: SearchRepository
) : UseCase<Media, String>() {

    override suspend fun run(params: String?): Media {
        return searchRepository.search(params!!)
    }

}