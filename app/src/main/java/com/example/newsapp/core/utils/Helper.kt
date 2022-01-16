package com.example.newsapp.core.utils

import java.text.SimpleDateFormat
import java.util.*

object Helper {
    fun dateFormatter(input: String): String {
        val dateFormat_yyyyMMddHHmmss = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH
        )
        val date = dateFormat_yyyyMMddHHmmss.parse(input)!!
        val simpleDateFormat = SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a")
        val dateTime = simpleDateFormat.format(date)
        return dateTime
    }
}