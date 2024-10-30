package com.nikitayasiulevich.edazavr.presentation.home

import com.nikitayasiulevich.edazavr.domain.model.Restaurant
import com.nikitayasiulevich.edazavr.domain.model.User

sealed class HomeScreenState {

    data object Initial : HomeScreenState()

    data object Loading : HomeScreenState()

    data class Restaurants(
        val user: User,
        val restaurants: List<Restaurant>
    ) : HomeScreenState()
}