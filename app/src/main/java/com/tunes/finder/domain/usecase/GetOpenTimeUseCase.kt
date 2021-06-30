package com.tunes.finder.domain.usecase

import com.tunes.finder.domain.repository.CacheRepository
import com.tunes.finder.domain.usecase.base.UseCase

class GetOpenTimeUseCase constructor(
    private val cacheRepository: CacheRepository
) : UseCase<String, Void>() {

    override suspend fun run(params: Void?): String {
        return cacheRepository.getLastOpenTime()
    }

}