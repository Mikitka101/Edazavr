package com.nikitayasiulevich.edazavr.data.model

import com.google.gson.annotations.SerializedName
import com.nikitayasiulevich.edazavr.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class RestaurantDto(
     @Serializable(with = UUIDSerializer::class)
     @SerializedName("id")val id: UUID,
     @Serializable(with = UUIDSerializer::class)
     @SerializedName("admin")val admin: UUID,
     @SerializedName("name") val name: String,
     @SerializedName("description") val description: String,
     @SerializedName("category") val category: String,
     @SerializedName("address") val address: String,
     @SerializedName("photo") val photo: String,
     @SerializedName("active") val active: Boolean,
     @SerializedName("banned") val banned: Boolean,
)
