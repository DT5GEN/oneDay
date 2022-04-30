package com.task.oneday.domain

import com.task.oneday.api.NasaApi
import com.task.oneday.api.PictureOfTheDayResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NasaRepositoryImpl : NasaRepository {


    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.nasa.gov/")
        .client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
            .build()
        )
        .build()
        .create(NasaApi::class.java)


    override suspend fun pictureOfTheDay(): PictureOfTheDayResponse = api.pictureOfTheDay("Igvzb12oRMLhy1hTJpk2qzhZklXEpFoMFuo0gWVo")


}