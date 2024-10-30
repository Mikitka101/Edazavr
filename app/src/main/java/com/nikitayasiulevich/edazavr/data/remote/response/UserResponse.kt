package com.nikitayasiulevich.edazavr.data.remote.response

import com.nikitayasiulevich.edazavr.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserResponse(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val login: String,
    val roles: List<String>
)
