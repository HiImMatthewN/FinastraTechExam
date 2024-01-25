package com.nantes.matthew.finastratechexam.photos.domain.model

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

data class Photo(
    val id: String,
    val url: String,
    val likes: Int,
    val color:String,
    val author: Author,
    val dateCreated: Date
) {

    fun getDateCreateString(): String {
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return formatter.format(dateCreated)
    }

}