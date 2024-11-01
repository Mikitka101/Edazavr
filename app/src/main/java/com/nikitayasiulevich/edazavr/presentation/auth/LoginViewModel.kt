package com.nikitayasiulevich.edazavr.presentation.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.nikitayasiulevich.edazavr.data.remote.ApiService
import com.nikitayasiulevich.edazavr.data.remote.request.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private var service = ApiService.create(application)
    private val context = application

    var isAuthorized = MutableLiveData<Boolean>(false)
    var showError = MutableLiveData<Boolean>(false)

    fun login(login: String, password: String) {
        val loginRequest = LoginRequest(login, password)
        viewModelScope.launch {
            when (service.authorize(context, loginRequest)) {
                is Either.Left -> {
                    showError.value =  true
                    isAuthorized.value = false
                }

                is Either.Right -> {
                    isAuthorized.value = true
                    showError.value = false
                }
            }
        }
    }
}