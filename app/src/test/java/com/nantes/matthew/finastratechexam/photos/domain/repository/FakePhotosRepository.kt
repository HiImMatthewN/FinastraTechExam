package com.nantes.matthew.finastratechexam.photos.domain.repository

import com.nantes.matthew.finastratechexam.core.util.Resource
import com.nantes.matthew.finastratechexam.photos.domain.model.Photo
import com.nantes.matthew.finastratechexam.test_utils.TestData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePhotosRepository:PhotoRepository {




    override fun getPhotos(page: Int): Flow<Resource<List<Photo>>> {
        return flow {
            emit(Resource.success(TestData.photos.filter {photo -> photo.page == page  }))
        }
    }
}