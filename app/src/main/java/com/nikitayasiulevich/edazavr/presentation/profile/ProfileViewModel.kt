package com.nikitayasiulevich.edazavr.presentation.profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikitayasiulevich.edazavr.data.repository.UserRepository
import com.nikitayasiulevich.edazavr.utils.Constants
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application
    private val initialState = ProfileScreenState.Initial

    private val _screenState = MutableLiveData<ProfileScreenState>(initialState)
    val screenState: LiveData<ProfileScreenState> = _screenState

    private val userRepository = UserRepository(application)

    init {
        _screenState.value = ProfileScreenState.Loading
        loadData()
    }

    private fun loadData() {
        _screenState.value = ProfileScreenState.Loading
        viewModelScope.launch {
            val user = userRepository.getUserData()
            _screenState.value = ProfileScreenState.UserInfo(user = user)
        }
    }

    fun logOut() {
        _screenState.value = ProfileScreenState.Initial
        context.getSharedPreferences(
            Constants.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        ).edit()
            .putBoolean("is_auth", false)
            .putString("access", "")
            .putString("refresh", "")
            .apply()
    }
}