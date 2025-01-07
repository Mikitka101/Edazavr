package com.nikitayasiulevich.edazavr.domain.model

import kotlinx.serialization.descriptors.SerialDescriptor
import java.util.UUID

data class Dish(
    val id: UUID,
    val name: String,
    val description: String,
    val photoUrl: String,
    val price: Float,
)
