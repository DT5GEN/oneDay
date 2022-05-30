package com.task.oneday.viewmodel

import com.task.oneday.api.PictureOfTheDayResponseData


sealed class NASAState{
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData):NASAState()
    data class Loading(val progress: Int?):NASAState()
    data class Error(val error: Throwable):NASAState()
}