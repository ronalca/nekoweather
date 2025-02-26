package com.ralcala.nekoweather.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun tempKelvinToCelsius(tempK: Double): Double {
    return tempK - 273.15
}

fun unixToLocalTime(unixTime: Long): String {
    val date = Date(unixTime * 1000)
    val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
    format.timeZone = TimeZone.getDefault()
    return format.format(date)
}
