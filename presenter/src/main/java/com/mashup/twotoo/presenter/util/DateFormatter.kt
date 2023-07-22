package com.mashup.twotoo.presenter.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    private const val DATE_FORMAT = "yyyy-MM-dd"

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat(DATE_FORMAT)

    fun getDateStrByStr(date: String): String {
        val date = getDateByStr(date)
        return getFormattedStrByDate(date)
    }

    fun getDateByStr(date: String): Date {
        return dateFormat.parse(date)
    }

    fun getFormattedStrByDate(date: Date): String {
        return dateFormat.format(date)
    }
}
