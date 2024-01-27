package com.nantes.matthew.finastratechexam.photos.data.repository

import com.nantes.matthew.finastratechexam.core.util.Resource
import com.nantes.matthew.finastratechexam.core.util.networkBoundResource
import com.nantes.matthew.finastratechexam.photos.data.data_source.PhotoApi
import com.nantes.matthew.finastratechexam.photos.data.data_source.PhotoDao
import com.nantes.matthew.finastratechexam.photos.data.entities.local.AuthorEntity
import com.nantes.matthew.finastratechexam.photos.data.entities.local.PhotoInfoEntity
import com.nantes.matthew.finastratechexam.photos.data.entities.local.toDomain
import com.nantes.matthew.finastratechexam.photos.domain.model.Photo
import com.nantes.matthew.finastratechexam.photos.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val PHOTOS_PER_PAGE = 50

class PhotoRepositoryImpl @Inject constructor(
    private val api: PhotoApi,
    private val dao: PhotoDao
) : PhotoRepository {
    override fun getPhotos(page: Int): Flow<Resource<List<Photo>>> {
        return networkBoundResource(
            query = {
                dao.getPhotos(
                    page = page,
                    limit = PHOTOS_PER_PAGE
                ).map { entities ->
                    entities.map { entity ->
                        entity.toDomain()
                    }
                }
            },
            shouldFetch = { true },
            fetch = {
                api.getPhotos(
                    page = page,
                    numberOfPhotosPerPage = PHOTOS_PER_PAGE
                )
            },
            saveFetchResult = { response ->
                if (response.isSuccessful) {
                    val photoRemoteList = response.body() ?: emptyList()

                    val photoEntities =
                        photoRemoteList.map { photoRemote ->
                            PhotoInfoEntity(
                                id = photoRemote.id,
                                url = photoRemote.urls.full,
                                likes = photoRemote.likes,
                                authorId = photoRemote.author.id,
                                page = page,
                                dateCreated = photoRemote.getDateCreated()?.time ?: -1
                            )

                        }
                    val authorEntities =
                        photoRemoteList.map { photoRemote ->
                            AuthorEntity(
                                id = photoRemote.author.id,
                                username = photoRemote.author.name,
                                name = photoRemote.author.name,
                                profileImage = photoRemote.author.profileImage.medium
                            )
                        }
                    dao.savePhotosAndAuthors(
                        photoEntities = photoEntities,
                        authorEntities = authorEntities
                    )


                }

            }

        )

    }
}