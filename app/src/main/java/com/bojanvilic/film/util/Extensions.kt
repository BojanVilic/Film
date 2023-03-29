package com.bojanvilic.film.util

import java.time.LocalDateTime

fun LocalDateTime?.toReadableString(): String {
    this?.let {
        val monthName = it.month.name
        val minuteFormatted = String.format("%02d", it.minute)
        return "${it.dayOfMonth} $monthName ${it.year}, ${it.hour}:$minuteFormatted"
    }
    return ""
}
