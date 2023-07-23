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

    fun convertToLongDate(selectedDateMillis: Long?): String {
        selectedDateMillis?.let { selectedDate ->
            val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
            val date = Date(selectedDate)
            return dateFormat.format(date)
        }
        return ""
    }

    fun getCurrentDate(): String {
        return convertToLongDate(Calendar.getInstance().time.time)
    }

    fun getDaysAfter(selectDate: String, daysAfter: Int = 22): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val date = dateFormat.parse(selectDate)
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_YEAR, daysAfter)
        return convertToLongDate(calendar.time.time)
    }
}
