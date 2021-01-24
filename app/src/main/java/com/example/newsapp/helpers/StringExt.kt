package com.example.newsapp.helpers

import android.net.ParseException
import com.example.newsapp.App
import com.idn.fahru.beritaapp.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToLong


val String.toRelativeDate: String
    get() {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("GMT")
        val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", Locale.forLanguageTag("in"))
        val minuteAgo = App.context.getString(R.string.time_minute_ago)
        val minutesAgo = App.context.getString(R.string.time_minutes_ago)
        val hourAgo = App.context.getString(R.string.time_hour_ago)
        val hoursAgo = App.context.getString(R.string.time_hours_ago)
        try {
            val date = inputFormat.parse(this)
            val diff: Long = System.currentTimeMillis() - (date?.time ?: 0)
            val hours = (diff / (60 * 60 * 1000).toFloat()).roundToLong()
            val minutes = (diff / (60 * 1000).toFloat()).roundToLong()

            return if (hours < 24) {
                when {
                    minutes < 2 -> "$minutes $minuteAgo"
                    minutes < 60 -> "$minutes $minutesAgo"
                    hours < 2 -> "$hours $hourAgo"
                    else -> "$hours $hoursAgo"
                }
            } else {
                date?.let { outputFormat.format(it) } ?: ""

            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }