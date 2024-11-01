package com.nikitayasiulevich.edazavr.data.model

import com.nikitayasiulevich.edazavr.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserDTO(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val login: String,
    val name: String,
    val roles: List<String>
)
