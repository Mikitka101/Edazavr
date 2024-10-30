package com.nikitayasiulevich.edazavr.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String
)
