package com.nantes.matthew.finastratechexam.photos.domain.model

data class Photo(
    val url:String,
    val likes:Int,
    val author: Author
)