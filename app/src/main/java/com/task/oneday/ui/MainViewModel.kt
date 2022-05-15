package com.task.oneday.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.task.oneday.domain.NasaRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(val repository: NasaRepository) : ViewModel() {


    private val _loading = MutableStateFlow(false)
    val loading: Flow<Boolean> = _loading


    private val _image: MutableStateFlow<String?> = MutableStateFlow(null)
    val image: Flow<String?> = _image


    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error: Flow<String> = _error

    val _explanation: MutableSharedFlow<String> = MutableSharedFlow()
    val explanation: Flow<String> = _explanation

    fun requestPictureOfTheDay() {

        _loading.value = true

        viewModelScope.launch {

            //  _loading.emit(true) следит ,чтобы корутина которая принимает не обогнала корутину, которая отправляет

            try {
                val url = repository.pictureOfTheDay().url
                _image.emit(url)

            } catch (exc: IOException) {
                _error.emit("Error network")
            }
            _loading.emit(false)

        }
  viewModelScope.launch {

            //  _loading.emit(true) следит ,чтобы корутина которая принимает не обогнала корутину, которая отправляет

            try {
                val exp = repository.pictureOfTheDay().explanation

                _explanation.emit(exp)

            } catch (exc: IOException) {
                _error.emit("Error network")
            }
            _loading.emit(false)

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