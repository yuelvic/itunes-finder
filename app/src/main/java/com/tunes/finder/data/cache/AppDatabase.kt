package com.tunes.finder.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tunes.finder.data.cache.dao.CacheDao
import com.tunes.finder.data.cache.entity.Cache

@Database(entities = [Cache::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cacheDao(): CacheDao
}