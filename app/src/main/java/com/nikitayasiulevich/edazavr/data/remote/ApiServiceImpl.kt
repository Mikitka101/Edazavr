package com.nikitayasiulevich.edazavr.data.remote

import android.content.Context
import android.util.Log
import com.nikitayasiulevich.edazavr.data.model.RestaurantsListDto
import com.nikitayasiulevich.edazavr.data.remote.request.LoginRequest
import com.nikitayasiulevich.edazavr.data.remote.response.AuthResponse
import com.nikitayasiulevich.edazavr.data.remote.response.UserResponse
import com.nikitayasiulevich.edazavr.domain.model.Restaurant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class ApiServiceImpl(
    private val client: HttpClient,
) : ApiService {

    override suspend fun authorize(context: Context, loginRequest: LoginRequest): AuthResponse {

        val response: AuthResponse = client.post(urlString = HttpRouts.AUTH) {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }.body()
        Log.d("MYLOG", response.accessToken)
        Log.d("MYLOG", response.refreshToken)

        val sharedPref = context.getSharedPreferences(
            "com.nikitayasiulevich.edazavr.tokens",
            Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            putString("access", response.accessToken)
            putString("refresh", response.refreshToken)
            putBoolean("is_auth", true)
            apply()
        }
        return response
    }

    override suspend fun getPersonalData(): UserResponse {

        return client.get(
            urlString = HttpRouts.GET_PERSONAL_DATA
        ) {
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getRestaurants(): RestaurantsListDto {

        return client.get(
            urlString = HttpRouts.GET_RESTAURANTS
        ) {
            contentType(ContentType.Application.Json)
        }.body()
    }
}