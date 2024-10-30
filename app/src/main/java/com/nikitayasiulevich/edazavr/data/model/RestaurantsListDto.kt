package com.nikitayasiulevich.edazavr.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantsListDto(
    val restaurants: List<RestaurantDto>,
)
