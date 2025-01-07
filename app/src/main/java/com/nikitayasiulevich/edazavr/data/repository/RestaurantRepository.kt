package com.nikitayasiulevich.edazavr.data.repository

import android.app.Application
import com.nikitayasiulevich.edazavr.data.mapper.RestaurantMapper
import com.nikitayasiulevich.edazavr.data.remote.ApiService
import com.nikitayasiulevich.edazavr.data.remote.request.LoginRequest
import com.nikitayasiulevich.edazavr.domain.model.Dish
import com.nikitayasiulevich.edazavr.domain.model.Restaurant
import com.nikitayasiulevich.edazavr.domain.model.User

class RestaurantRepository(
    private val application: Application
) {
    private val mapper = RestaurantMapper()
    private val apiService = ApiService.create(application)

    private val _restaurants = mutableListOf<Restaurant>()
    val restaurants: List<Restaurant>
        get() = _restaurants.toList()

    suspend fun loadRestaurants(): List<Restaurant> {

        val response = apiService.getRestaurants()
        _restaurants.clear()
        _restaurants.addAll(mapper.mapRestaurantsListDtoToRestaurants(response))

        return restaurants
    }

    suspend fun getRestaurant(restaurantId: String): Restaurant {

        val response = apiService.getRestaurant(restaurantId)

        return mapper.mapRestaurantDtoToRestaurant(response)
    }
}