package com.nikitayasiulevich.edazavr.data.model

import com.google.gson.annotations.SerializedName
import com.nikitayasiulevich.edazavr.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class DishDto(
     @Serializable(with = UUIDSerializer::class)
     @SerializedName("id")val id: UUID,
     @Serializable(with = UUIDSerializer::class)
     @SerializedName("restaurant")val restaurant: UUID,
     @SerializedName("name") val name: String,
     @SerializedName("description") val description: String,
     @SerializedName("photo") val photo: String,
     @SerializedName("price") val price: Float,
     @SerializedName("active") val active: Boolean,
     @SerializedName("banned") val banned: Boolean,
)
