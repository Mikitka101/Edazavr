package com.nikitayasiulevich.edazavr.presentation.dishes

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

class DishesViewModel(
    application: Application,
    restaurant: Restaurant
) : AndroidViewModel(application) {

    private val initialState = DishesScreenState.Initial

    private val _screenState = MutableLiveData<DishesScreenState>(initialState)
    val screenState: LiveData<DishesScreenState> = _screenState

    private val dishRepository = DishRepository(application)

    init {
        _screenState.value = DishesScreenState.Loading
        loadDishes(restaurant)
    }

    fun loadDishes(restaurant: Restaurant) {
        _screenState.value = DishesScreenState.Loading
        viewModelScope.launch {
            val dishes = dishRepository.loadDishes(restaurant)
            val user =
                User(id = UUID.randomUUID(), login = "client@gmail.com", roles = listOf("client"))
            _screenState.value =
                DishesScreenState.Dishes(user = user, restaurant = restaurant, dishes = dishes)
        }
    }

    fun addDishToShoppingCart(dish: Dish) {
        viewModelScope.launch {
            dishRepository.addDishToShoppingCart(dish)
        }
    }
}