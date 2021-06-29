package com.tunes.finder.data.remote

import com.tunes.finder.domain.model.Media
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search")
    suspend fun search(
        @Query("term") term: String,
        @Query("media") media: String? = "music",
        @Query("country") country: String? = "ph"
    ): Media

}