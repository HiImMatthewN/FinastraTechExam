package com.nantes.matthew.finastratechexam.photos.domain.repository

import com.nantes.matthew.finastratechexam.core.util.Resource
import com.nantes.matthew.finastratechexam.photos.domain.model.Photo

interface PhotoRepository {


    suspend fun getPhotos(): Resource<List<Photo>>

}