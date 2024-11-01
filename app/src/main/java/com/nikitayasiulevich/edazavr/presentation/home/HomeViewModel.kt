package com.nikitayasiulevich.edazavr.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikitayasiulevich.edazavr.data.repository.RestaurantRepository
import com.nikitayasiulevich.edazavr.data.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val initialState = HomeScreenState.Initial

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private val restaurantRepository = RestaurantRepository(application)
    private val userRepository = UserRepository(application)

    init {
        _screenState.value = HomeScreenState.Loading
        loadData()
    }

    private fun loadData() {
        _screenState.value = HomeScreenState.Loading
        viewModelScope.launch {
            val restaurants = restaurantRepository.loadRestaurants()
            val user = userRepository.getUserData()
            _screenState.value = HomeScreenState.Restaurants(user = user, restaurants = restaurants)
        }
    }
}