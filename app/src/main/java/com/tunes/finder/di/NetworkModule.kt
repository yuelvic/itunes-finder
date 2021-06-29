package com.tunes.finder.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.tunes.finder.data.remote.ApiService
import com.tunes.finder.data.repository.SearchRepositoryImpl
import com.tunes.finder.domain.repository.SearchRepository
import com.tunes.finder.domain.usecase.SearchUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), "https://itunes.apple.com") }

    single { createOkHttpClient(get()) }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }

}

fun createOkHttpClient(context: Context): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createSearchRepository(apiService: ApiService): SearchRepository {
    return SearchRepositoryImpl(apiService)
}

fun createSearchUseCase(searchRepository: SearchRepository) : SearchUseCase {
    return SearchUseCase(searchRepository)
}