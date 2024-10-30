package com.nikitayasiulevich.edazavr.data.remote

import android.content.Context
import android.util.Log
import com.nikitayasiulevich.edazavr.data.model.RestaurantsListDto
import com.nikitayasiulevich.edazavr.data.remote.request.LoginRequest
import com.nikitayasiulevich.edazavr.data.remote.request.RefreshTokenRequest
import com.nikitayasiulevich.edazavr.data.remote.response.AuthResponse
import com.nikitayasiulevich.edazavr.data.remote.response.TokenResponse
import com.nikitayasiulevich.edazavr.data.remote.response.UserResponse
import com.nikitayasiulevich.edazavr.domain.model.Restaurant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


interface ApiService {

    suspend fun authorize(context: Context, loginRequest: LoginRequest): AuthResponse

    suspend fun getPersonalData(): UserResponse

    suspend fun getRestaurants(): RestaurantsListDto

    //suspend fun getRestaurants(): RestaurantsListDto

    companion object {
        fun create(context: Context): ApiService {
            val sharedPreferences = context.getSharedPreferences(
                "com.nikitayasiulevich.edazavr.tokens",
                Context.MODE_PRIVATE
            )
            return ApiServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json(Json { ignoreUnknownKeys = true })
                    }
                    install(HttpTimeout) {
                        requestTimeoutMillis = 10_000
                    }
                    install(Auth) {
                        bearer {
                            loadTokens {
                                val accessFromSP =
                                    sharedPreferences.getString("access", "nothing") ?: "nothing"
                                val refreshFromSP =
                                    sharedPreferences.getString("refresh", "nothing") ?: "nothing"
                                BearerTokens(
                                    accessToken = accessFromSP,
                                    refreshToken = refreshFromSP
                                )
                            }
                            refreshTokens {
                                val currentRefreshToken =
                                    sharedPreferences.getString("refresh", "nothing")
                                        ?: "nothing"
                                val refreshTokenRequest = RefreshTokenRequest(
                                    token = currentRefreshToken
                                )
                                val response =
                                    client.post(urlString = HttpRouts.REFRESH) {
                                        contentType(ContentType.Application.Json)
                                        setBody(refreshTokenRequest)
                                    }
                                val tokenResponse: TokenResponse? =
                                    if (response.status != HttpStatusCode.Unauthorized) {
                                        response.body()
                                    } else {
                                        with(sharedPreferences.edit()) {
                                            putBoolean("is_auth", false)
                                            apply()
                                        }
                                        null
                                    }

                                with(sharedPreferences.edit()) {
                                    putString("access", tokenResponse?.token ?: "")
                                    apply()
                                }
                                BearerTokens(
                                    accessToken = tokenResponse?.token ?: "",
                                    refreshToken = currentRefreshToken
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
