package com.nikitayasiulevich.edazavr.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikitayasiulevich.edazavr.data.repository.DishRepository
import com.nikitayasiulevich.edazavr.data.repository.RestaurantRepository
import com.nikitayasiulevich.edazavr.domain.model.Dish
import com.nikitayasiulevich.edazavr.domain.model.Restaurant
import com.nikitayasiulevich.edazavr.domain.model.User
import kotlinx.coroutines.launch
import java.util.UUID

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val initialState = HomeScreenState.Initial

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private val restaurantRepository = RestaurantRepository(application)

    init {
        _screenState.value = HomeScreenState.Loading
        loadRestaurants()
    }

    fun loadRestaurants() {
        _screenState.value = HomeScreenState.Loading
        viewModelScope.launch {
            val restaurants = restaurantRepository.loadRestaurants()
            val user =
                User(id = UUID.randomUUID(), login = "client@gmail.com", roles = listOf("client"))
            _screenState.value = HomeScreenState.Restaurants(user = user, restaurants = restaurants)
        }
    }
}