package com.nantes.matthew.finastratechexam.photos.presentation

sealed class PhotoFeedIntent {

    data class UserScrolled(val isScrolling:Boolean):PhotoFeedIntent()
    data object PreviousPage : PhotoFeedIntent()
    data object NextPage : PhotoFeedIntent()
}