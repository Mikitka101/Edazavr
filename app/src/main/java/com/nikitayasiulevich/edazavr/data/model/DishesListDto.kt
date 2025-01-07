package com.nikitayasiulevich.edazavr.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DishesListDto(
    val dishes: List<DishDto>,
)
