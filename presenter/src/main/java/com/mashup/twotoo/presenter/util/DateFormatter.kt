package com.mashup.twotoo.presenter.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    private const val DATE_FORMAT = "yyyy-MM-dd"

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat(DATE_FORMAT)

    fun getDateStrBy(date: String): String {
        val date = dateFormat.parse(date)
        return dateFormat.format(date)
    }
}
