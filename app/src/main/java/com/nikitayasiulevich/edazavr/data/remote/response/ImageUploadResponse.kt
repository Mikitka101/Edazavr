package com.nikitayasiulevich.edazavr.data.remote.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class ImageUploadResponse(
    val id: String,
    val url: String,
)
