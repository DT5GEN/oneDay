package com.task.oneday.domain

import com.task.oneday.api.PictureOfTheDayResponseData

interface NasaRepository {

    suspend fun pictureOfTheDay(): PictureOfTheDayResponseData
}