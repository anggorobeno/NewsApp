package com.example.newsapp.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Helper {
    @RequiresApi(Build.VERSION_CODES.O)
    fun dateFormatter(input: String): String {
        val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDate.parse(input, firstApiFormat).toString()
    }


}