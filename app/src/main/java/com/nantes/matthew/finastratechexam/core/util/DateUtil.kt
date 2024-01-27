package com.nantes.matthew.finastratechexam.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {

    const val DATE_TIME_FORMAT = "yyyy-MM-d'T'HH:mm:ss'Z'"


    fun getDateFromString(format: String, dateString: String): Date {
        val dateFormatter = SimpleDateFormat(format, Locale.US)
        return try {
            dateFormatter.parse(dateString)!!
        } catch (e: Exception) {
            Date()
        }
    }
}


