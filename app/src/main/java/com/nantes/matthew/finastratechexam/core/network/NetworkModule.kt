package com.nantes.matthew.finastratechexam.core.network

import com.nantes.matthew.finastratechexam.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkHTTP(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val currentRequest = chain.request()
                val currentUrl = currentRequest.url
                val updatedUrl = currentUrl.newBuilder().addQueryParameter("client_id", BuildConfig.API_KEY).build()
                val updatedRequest = currentRequest.newBuilder().url(updatedUrl).build()

                chain.proceed(updatedRequest)
            }
            .connectTimeout(1, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(1, TimeUnit.MINUTES) // write timeout
            .readTimeout(1, TimeUnit.MINUTES) //read timeout
        return client.build()
    }

    @Provides
    @Singleton
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

}