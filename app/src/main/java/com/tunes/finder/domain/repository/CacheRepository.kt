package com.tunes.finder.domain.repository

interface CacheRepository {

    suspend fun getLastOpenTime(): String

    suspend fun setLastOpenTime(openTime: String)

}