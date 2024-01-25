package com.nantes.matthew.finastratechexam.photos.presentation

import com.nantes.matthew.finastratechexam.photos.domain.model.Photo

data class PhotoFeedState(
    val photos: List<Photo> = emptyList(),
    val showLoadingBar:Boolean = true
)