package com.nantes.matthew.finastratechexam.photos.data.module

import com.nantes.matthew.finastratechexam.photos.data.data_source.PhotoApi
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
    @Singleton
    fun providesPhotoRepository(api: PhotoApi): PhotoRepository = PhotoRepositoryImpl(
        api = api
    )


}