package com.mashup.twotoo.presenter.util

import android.annotation.SuppressLint
import android.util.Log
import com.mashup.twotoo.presenter.constant.TAG
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

    fun formatDateRange(startDate: String, endDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
        val outputFormat = SimpleDateFormat("yy/MM/dd", Locale.KOREA)

        val startDateObj = inputFormat.parse(startDate)
        val endDateObj = inputFormat.parse(endDate)

        val formattedStartDate = startDateObj?.let { outputFormat.format(it) }
        val formattedEndDate = endDateObj?.let { outputFormat.format(it) }

        return "$formattedStartDate ~ $formattedEndDate"
    }

    fun convertToIsoTime(dateTime: String): String? {
        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
        val date = formatter.parse(dateTime)

        val isoFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA)
        isoFormatter.timeZone = TimeZone.getTimeZone("UTC")
        Log.d(TAG, "convertToIsoTime:${isoFormatter.format(date)} ")
        return date?.let { isoFormatter.format(it) }
    }
}
