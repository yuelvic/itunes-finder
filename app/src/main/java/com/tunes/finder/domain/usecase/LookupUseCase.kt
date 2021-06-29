package com.tunes.finder.domain.usecase

import com.tunes.finder.domain.model.Media
import com.tunes.finder.domain.repository.SearchRepository
import com.tunes.finder.domain.usecase.base.UseCase

class LookupUseCase constructor(
    private val searchRepository: SearchRepository
) : UseCase<Media, Long>() {

    override suspend fun run(params: Long?): Media {
        return searchRepository.lookup(params!!)
    }

}