package com.nikitayasiulevich.edazavr.domain.model

data class User(
    val id: String,
    val login: String,
    val name: String,
    val roles: List<String>
)
