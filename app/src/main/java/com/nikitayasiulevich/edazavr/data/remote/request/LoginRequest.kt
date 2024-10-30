package com.nikitayasiulevich.edazavr.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val login: String,
    val password: String,
)
