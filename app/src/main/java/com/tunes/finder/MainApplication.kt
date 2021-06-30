package com.tunes.finder

import android.app.Application
import com.tunes.finder.di.AppModule
import com.tunes.finder.di.CacheModule
import com.tunes.finder.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(listOf(AppModule, NetworkModule, CacheModule))
        }
    }

}