package com.nikitayasiulevich.edazavr.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)
