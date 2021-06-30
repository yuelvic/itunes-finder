package com.tunes.finder.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    var resultCount: Int,
    @Json(name = "results")
    var result: List<Medium>
)

@JsonClass(generateAdapter = true)
data class Medium(
    var trackId: Long,
    var artistName: String,
    var trackName: String,
    @Json(name = "artworkUrl100")
    var artworkUrl: String,
    var trackPrice: Int?,
    @Json(name = "primaryGenreName")
    var genre: String,
    @Json(name = "trackTimeMillis")
    var trackLength: Long
)