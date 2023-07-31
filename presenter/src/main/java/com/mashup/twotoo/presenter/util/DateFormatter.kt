package com.mashup.twotoo.presenter.util

import android.util.Log
import com.mashup.twotoo.presenter.constant.TAG
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateFormatter {

    fun getDateStrByStr(date: String): String {
        val dateTime = getDateTimeByStr(date)
        return getDateStrByDate(dateTime)
    }

    fun dateConvertToPlusNineTime(date: String): String {
        val dateTime =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault()).parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = dateTime
        calendar.add(Calendar.HOUR_OF_DAY, 9)
        val updateDate = calendar.time
        val test = SimpleDateFormat("MM-dd", Locale.getDefault()).format(updateDate)
        return test
    }

    fun dateConvertToPlusNineDate(date: String): Date {
        val dateTime =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault()).parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = dateTime
        calendar.add(Calendar.HOUR_OF_DAY, 9)
        return calendar.time
    }

    fun getCurrentTimeWithPlusNine(): Date {
        val curDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault()).format(Date())
        return dateConvertToPlusNineDate(curDate)
    }

    fun getCurrentDateWithPlusNine(): String {
        val curDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault()).format(Date())
        return dateConvertToPlusNineTime(curDate)
    }
    fun getDateTimeByStr(date: String): Date {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)
    }

    fun getDateStrByDate(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
    }

    fun getDateStrMonthDay(date: Date): String {
        return SimpleDateFormat("MM-dd", Locale.getDefault()).format(date)
    }

    fun get24HourStrByStr(date: String): String {
        val dateTime =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault()).parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = dateTime
        calendar.add(Calendar.HOUR_OF_DAY, 9)
        val updateDate = calendar.time

        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(updateDate)
    }

    fun convertToLongDate(selectedDateMillis: Long?): String {
        selectedDateMillis?.let { selectedDate ->
            val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
            val date = Date(selectedDate)
            Log.d(TAG, "convertToLongDate: ${dateFormat.format(date)}")
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
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.HOUR_OF_DAY, -9)
        val updateDate = calendar.time

        val isoFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA)
        Log.d(TAG, "convertToIsoTime: ${isoFormatter.format(updateDate)}")
        return updateDate?.let { isoFormatter.format(it) }
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

    fun unixTimeToUtcTime(currentTimeMillis: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentTimeMillis
        calendar.add(Calendar.HOUR_OF_DAY, 9)
        return calendar.timeInMillis
    }
}
