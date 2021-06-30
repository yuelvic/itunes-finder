package com.tunes.finder.data.repository

import com.tunes.finder.data.cache.AppDatabase
import com.tunes.finder.data.cache.entity.Cache
import com.tunes.finder.domain.repository.CacheRepository

class CacheRepositoryImpl(private val appDatabase: AppDatabase) : CacheRepository {

    override suspend fun getLastOpenTime(): String {
        return appDatabase.cacheDao().getLastOpenedTime()
    }

    override suspend fun setLastOpenTime(openTime: String) {
        return appDatabase.cacheDao().setLastOpenedTime(
            Cache(openTime = openTime, id = System.currentTimeMillis())
        )
    }

}