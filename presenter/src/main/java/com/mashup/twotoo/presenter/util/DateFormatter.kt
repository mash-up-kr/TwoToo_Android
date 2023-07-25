package com.mashup.twotoo.presenter.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun getDateStrByStr(date: String): String {
        val dateTime = getDateTimeByStr(date)
        return getDateStrByDate(dateTime)
    }

    fun getDateTimeByStr(date: String): Date {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)
    }

    fun getDateStrByDate(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
    }

    fun get24HourStrByStr(date: String): String {
        val dateTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault()).parse(date)
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(dateTime)
    }
}
