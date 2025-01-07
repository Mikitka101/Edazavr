package com.nikitayasiulevich.edazavr.data.repository

import android.app.Application
import com.nikitayasiulevich.edazavr.data.mapper.DishMapper
import com.nikitayasiulevich.edazavr.data.remote.ApiService
import com.nikitayasiulevich.edazavr.domain.model.Dish
import com.nikitayasiulevich.edazavr.domain.model.Restaurant

class DishRepository(
    private val application: Application
) {
    private val mapper = DishMapper()
    private val apiService = ApiService.create(application)

    private val _dishes = mutableListOf<Dish>()
    val dishes: List<Dish>
        get() = _dishes.toList()

    suspend fun loadDishes(restaurant: Restaurant): List<Dish> {
        val response = apiService.getDishes(restaurant.id)
        _dishes.clear()
        _dishes.addAll(mapper.mapDishesListDtoToDishes(response))

        return dishes
    }

    suspend fun addDishToShoppingCart(dish: Dish) {

    }
}