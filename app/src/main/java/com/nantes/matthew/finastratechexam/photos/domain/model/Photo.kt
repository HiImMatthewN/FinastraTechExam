package com.nantes.matthew.finastratechexam.photos.domain.model

import java.util.Date

data class Photo(
    val id:String,
    val url:String,
    val likes:Int,
    val author: Author,
    val dateCreated:Date
)