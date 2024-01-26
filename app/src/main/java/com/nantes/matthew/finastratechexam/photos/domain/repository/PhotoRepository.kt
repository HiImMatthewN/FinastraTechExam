package com.nantes.matthew.finastratechexam.photos.domain.repository

import com.nantes.matthew.finastratechexam.core.util.Resource
import com.nantes.matthew.finastratechexam.photos.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {


     fun getPhotos(page:Int): Flow<Resource<List<Photo>>>

}