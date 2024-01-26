package com.nantes.matthew.finastratechexam.photos.data.module

import com.nantes.matthew.finastratechexam.core.local.AppDatabase
import com.nantes.matthew.finastratechexam.photos.data.data_source.PhotoApi
import com.nantes.matthew.finastratechexam.photos.data.data_source.PhotoDao
import com.nantes.matthew.finastratechexam.photos.data.repository.PhotoRepositoryImpl
import com.nantes.matthew.finastratechexam.photos.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class PhotoModule {


    @Provides
    fun providesPhotoApi(retrofit: Retrofit): PhotoApi = retrofit.create(PhotoApi::class.java)


    @Provides
    fun providesPhotoDao(appDatabase: AppDatabase): PhotoDao = appDatabase.photoDao()

    @Provides
    @Singleton
    fun providesPhotoRepository(api: PhotoApi, dao: PhotoDao): PhotoRepository =
        PhotoRepositoryImpl(
            api = api,
            dao = dao
        )


}