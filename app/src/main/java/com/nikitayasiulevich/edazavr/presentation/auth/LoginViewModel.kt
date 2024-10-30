package com.nikitayasiulevich.edazavr.presentation.auth

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitayasiulevich.edazavr.data.remote.ApiService
import com.nikitayasiulevich.edazavr.data.remote.request.LoginRequest
import com.nikitayasiulevich.edazavr.data.remote.response.AuthResponse
import kotlinx.coroutines.launch

class LoginViewModel(
    val application: Application
) : ViewModel() {

    private var service = ApiService.create(application)

    val tokens = MutableLiveData<AuthResponse>(AuthResponse("", ""))

    val isAuth = MutableLiveData<Boolean>(false)

    val sharedPreferences = application.getSharedPreferences(
        "com.nikitayasiulevich.edazavr.tokens",
        Context.MODE_PRIVATE
    )

    fun login(login: String, password: String) {
        val loginRequest = LoginRequest(login, password)
        viewModelScope.launch {
            tokens.value = service.authorize(application, loginRequest)
        }
    }
}