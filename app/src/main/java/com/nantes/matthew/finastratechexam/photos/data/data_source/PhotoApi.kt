package com.nantes.matthew.finastratechexam.photos.data.data_source

import com.nantes.matthew.finastratechexam.photos.data.entities.PhotoRemote
import retrofit2.Response
import retrofit2.http.GET

interface PhotoApi {

    @GET("/photos")
    suspend fun getPhotos(): Response<List<PhotoRemote>>


}