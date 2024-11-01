package com.nikitayasiulevich.edazavr.data.repository

import android.app.Application
import arrow.core.Either
import com.nikitayasiulevich.edazavr.data.mapper.UserMapper
import com.nikitayasiulevich.edazavr.data.remote.ApiService
import com.nikitayasiulevich.edazavr.domain.model.User

class UserRepository(
    application: Application
) {
    private val mapper = UserMapper()
    private val apiService = ApiService.create(application)

    private lateinit var _currentUser: User
    val currentUser: User
        get() = _currentUser

    suspend fun getUserData(): User {
        val response = apiService.getPersonalData()

        _currentUser = when (response) {
            is Either.Left -> User("", "", "", listOf())
            is Either.Right -> mapper.mapDtoToEntity(response.value)
        }

        return currentUser
    }
}