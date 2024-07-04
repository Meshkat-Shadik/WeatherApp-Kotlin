package com.example.weatherapp.core.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

class DateTimeUtils {

    companion object {
        //get 1720116736 to 05 Jan 1970 10:48:36 PM
        @SuppressLint("SimpleDateFormat")
        fun convertUnixTimeToDateTime(unixTime: Long): String {
            return SimpleDateFormat("dd MMM yyyy hh:mm:ss a").format(Date(unixTime * 1000))
        }

    }

}