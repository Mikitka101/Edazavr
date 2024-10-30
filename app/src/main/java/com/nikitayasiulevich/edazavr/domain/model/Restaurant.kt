package com.nikitayasiulevich.edazavr.domain.model

data class Restaurant(
    val isActive: Boolean,
    val address: String,
    val admin: String,
    val isbBanned: Boolean,
    val category: String,
    val description: String,
    val id: String,
    val name: String,
    val photoUrl: String
)