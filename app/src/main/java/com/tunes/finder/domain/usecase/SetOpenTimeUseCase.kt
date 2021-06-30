package com.tunes.finder.domain.usecase

import com.tunes.finder.domain.repository.CacheRepository
import com.tunes.finder.domain.usecase.base.UseCase

class SetOpenTimeUseCase constructor(
    private val cacheRepository: CacheRepository
) : UseCase<Unit, String>() {

    override suspend fun run(params: String?) {
        return cacheRepository.setLastOpenTime(params!!)
    }

}