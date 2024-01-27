package com.nantes.matthew.finastratechexam.photos.data.data_source

import com.nantes.matthew.finastratechexam.photos.data.entities.remote.PhotoRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("/photos?per_page=50")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") numberOfPhotosPerPage: Int,
    ): Response<List<PhotoRemote>>


}