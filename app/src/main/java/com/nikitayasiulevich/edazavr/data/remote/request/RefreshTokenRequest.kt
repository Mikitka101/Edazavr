package com.nikitayasiulevich.edazavr.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequest(
    val token: String
)
