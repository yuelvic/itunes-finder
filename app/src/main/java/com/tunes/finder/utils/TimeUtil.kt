package com.tunes.finder.utils

import java.util.*
import java.util.concurrent.TimeUnit

object TimeUtil {
    fun toTrackLength(millis: Long): String {
        return "${TimeUnit.MILLISECONDS.toMinutes(millis)}:${
            TimeUnit.MILLISECONDS.toSeconds(millis) - 
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))}"
    }

    fun current(): String {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }
        return "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH)}-${calendar.get(Calendar.DAY_OF_MONTH)} " +
                "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"
    }
}