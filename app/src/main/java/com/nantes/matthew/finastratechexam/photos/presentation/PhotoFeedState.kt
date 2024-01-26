package com.nantes.matthew.finastratechexam.photos.presentation

import com.nantes.matthew.finastratechexam.photos.domain.model.Photo

data class PhotoFeedState(
    val photos: List<Photo> = emptyList(),
    val showPreviousButton:Boolean = false,
    val showNextButton:Boolean = false,
    val showProgressBar:Boolean = true,
    val currentPage:Int = 1,
    val isScrolling:Boolean = false
)