package com.tunes.finder.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tunes.finder.data.cache.entity.Cache

@Dao
interface CacheDao {

    @Query("SELECT open_time FROM Cache ORDER BY id DESC LIMIT 1")
    suspend fun getLastOpenedTime(): String

    @Insert
    suspend fun setLastOpenedTime(openTime: Cache)

}