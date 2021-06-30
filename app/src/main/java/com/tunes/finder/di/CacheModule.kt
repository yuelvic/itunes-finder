package com.tunes.finder.di

import android.content.Context
import androidx.room.Room
import com.tunes.finder.data.cache.AppDatabase
import com.tunes.finder.data.repository.CacheRepositoryImpl
import com.tunes.finder.domain.repository.CacheRepository
import com.tunes.finder.domain.usecase.GetOpenTimeUseCase
import com.tunes.finder.domain.usecase.SetOpenTimeUseCase
import org.koin.dsl.module

val CacheModule = module {
    single { createAppDatabase(get()) }

    single { createCacheRepository(get()) }

    single { createGetOpenTimeUseCase(get()) }

    single { createSetOpenTimeUseCase(get()) }
}

fun createAppDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "tunes-db").build()
}

fun createCacheRepository(appDatabase: AppDatabase): CacheRepository {
    return CacheRepositoryImpl(appDatabase)
}

fun createGetOpenTimeUseCase(cacheRepository: CacheRepository): GetOpenTimeUseCase {
    return GetOpenTimeUseCase(cacheRepository)
}

fun createSetOpenTimeUseCase(cacheRepository: CacheRepository): SetOpenTimeUseCase {
    return SetOpenTimeUseCase(cacheRepository)
}