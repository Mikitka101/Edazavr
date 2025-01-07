package com.nikitayasiulevich.edazavr.presentation.dishes

import com.nikitayasiulevich.edazavr.domain.model.Dish
import com.nikitayasiulevich.edazavr.domain.model.Restaurant
import com.nikitayasiulevich.edazavr.domain.model.User

sealed class DishesScreenState {

    data object Initial : DishesScreenState()

    data object Loading : DishesScreenState()

    data class Dishes(
        val restaurant: Restaurant,
        val dishes: List<Dish>,
    ) : DishesScreenState()
}