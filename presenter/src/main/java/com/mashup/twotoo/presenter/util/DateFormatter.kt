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
        if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
            val inputFormat = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
            val outputFormat = SimpleDateFormat("yy/MM/dd", Locale.KOREA)

            val startDateObj = inputFormat.parse(startDate)
            val endDateObj = inputFormat.parse(endDate)

            val formattedStartDate = startDateObj?.let { outputFormat.format(it) }
            val formattedEndDate = endDateObj?.let { outputFormat.format(it) }

            return "$formattedStartDate ~ $formattedEndDate"
        }
        return ""
    }

    fun convertToIsoTime(dateTime: String): String? {
        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
        val date = formatter.parse(dateTime)

        val isoFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA)
        return date?.let { isoFormatter.format(it) }
    }

    fun convertIsoTimeToString(isoTime: String): String? {
        if (isoTime.isNotEmpty()) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA)
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val sourceDate = dateFormat.parse(isoTime)
            val targetDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
            return sourceDate?.let { targetDateFormat.format(it) }
        }
        return ""
    }
}
