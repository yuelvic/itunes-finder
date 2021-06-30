package com.tunes.finder.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cache(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "open_time") val openTime: String?
)