package com.tunes.finder.utils

import java.util.concurrent.TimeUnit

object TimeUtil {
    fun toTrackLength(millis: Long): String {
        return "${TimeUnit.MILLISECONDS.toMinutes(millis)}:${
            TimeUnit.MILLISECONDS.toSeconds(millis) - 
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))}"
    }
}