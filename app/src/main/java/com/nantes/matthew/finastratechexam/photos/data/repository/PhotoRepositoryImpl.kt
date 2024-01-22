package com.nantes.matthew.finastratechexam.photos.data.repository

import com.nantes.matthew.finastratechexam.core.util.DateUtil
import com.nantes.matthew.finastratechexam.core.util.Resource
import com.nantes.matthew.finastratechexam.core.util.toReadableMessage
import com.nantes.matthew.finastratechexam.photos.data.data_source.PhotoApi
import com.nantes.matthew.finastratechexam.photos.domain.model.Author
import com.nantes.matthew.finastratechexam.photos.domain.model.Photo
import com.nantes.matthew.finastratechexam.photos.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val api: PhotoApi) : PhotoRepository {
    override suspend fun getPhotos(): Resource<List<Photo>> {
        return try {
            val response = api.getPhotos()
            if (!response.isSuccessful)
                Resource.error(response.message(), null)

            val photoRemoteList = response.body() ?: emptyList()
            val photos = photoRemoteList.map { photoRemote ->
                Photo(
                    id = photoRemote.id,
                    url = photoRemote.urls.full,
                    author = Author(
                        id = photoRemote.author.id,
                        name = photoRemote.author.name,
                        username = photoRemote.author.userName,
                        profileImage = photoRemote.author.profileImage.small,
                    ),
                    likes = photoRemote.likes,
                    dateCreated = DateUtil.getDateFromString(
                        format = DateUtil.DATE_TIME_FORMAT,
                        dateString = photoRemote.dateCreated
                    )
                )
            }

            Resource.success(photos)

        } catch (e: Exception) {
            Resource.error(e.toReadableMessage())
        }
    }
}