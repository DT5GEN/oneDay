package com.task.oneday.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.task.oneday.domain.NasaRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(val repository: NasaRepository) : ViewModel() {


    fun requestPictureOfTheDay() {

        viewModelScope.launch {
            val url = repository.pictureOfTheDay().url


        }


//  Вариант запуска асинхронных корутинов
//        viewModelScope.launch {
//            val deffered = viewModelScope.async {
//                repository.pictureOfTheDay()
//            }
//
//            val  deferred2 = viewModelScope.async {
//                repository.pictureOfTheDay()
//            }
//
//            deffered.await()
//            deferred2.await()
//        }


    }


}

class MainViewModelFactory(val repository: NasaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(repository) as T

}