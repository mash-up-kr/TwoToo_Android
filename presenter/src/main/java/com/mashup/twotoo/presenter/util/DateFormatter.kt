package com.mashup.twotoo.presenter.util

import android.util.Log
import com.mashup.twotoo.presenter.constant.TAG
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateFormatter {

    fun calDdayFromEndDate(endDate: String): Long {
        val timeZone = TimeZone.getTimeZone("UTC")
        val calendar = Calendar.getInstance(timeZone)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)
        simpleDateFormat.timeZone = timeZone

        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)
        val endDate = formatter.parse(endDate)
        Log.d(TAG, "calDdayFromEndDate: $endDate")
        val currentDateString = simpleDateFormat.format(calendar.time)
        val currentDate = formatter.parse(currentDateString)
        Log.d(TAG, "calDdayFromEndDate: days$currentDate")
        val diff: Long = endDate.time - currentDate.time
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        Log.d(TAG, "calDdayFromEndDate: days$days")
        return days
    }

    fun dateConvertToPlusNineTime(date: String, formatStr: String = "MM-dd"): String {
        val dateTime =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault()).parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = dateTime
        calendar.add(Calendar.HOUR_OF_DAY, 9)
        val updateDate = calendar.time
        val test = SimpleDateFormat(formatStr, Locale.getDefault()).format(updateDate)
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

    fun getDaysAfter(selectDate: String, daysAfter: Int = 21): String {
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
