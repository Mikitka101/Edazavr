package com.nikitayasiulevich.edazavr.data.remote

import android.content.Context
import arrow.core.Either
import com.nikitayasiulevich.edazavr.data.model.RestaurantsListDto
import com.nikitayasiulevich.edazavr.data.remote.request.LoginRequest
import com.nikitayasiulevich.edazavr.data.remote.response.AuthResponse
import com.nikitayasiulevich.edazavr.data.model.UserDTO
import com.nikitayasiulevich.edazavr.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType


class ApiServiceImpl(
    private val client: HttpClient,
) : ApiService {

    override suspend fun authorize(
        context: Context,
        loginRequest: LoginRequest
    ): Either<Exception, AuthResponse> {

        val sharedPref = context.getSharedPreferences(
            Constants.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )

        return try {

            val response: HttpResponse = client.post(urlString = HttpRouts.AUTH) {
                contentType(ContentType.Application.Json)
                setBody(loginRequest)
            }

            if (response.status != HttpStatusCode.OK) {
                Either.Left(Exception())
            }

            val autResponse = response.body<AuthResponse>()

            with(sharedPref.edit()) {
                putString("access", autResponse.accessToken)
                putString("refresh", autResponse.refreshToken)
                putBoolean("is_auth", true)
                apply()
            }

            Either.Right(autResponse)
        } catch (e: Exception) {
            e.printStackTrace()

            with(sharedPref.edit()) {
                putString("access", "")
                putString("refresh", "")
                putBoolean("is_auth", false)
                apply()
            }

            Either.Left(e)
        }
    }

    override suspend fun getPersonalData(): Either<Exception, UserDTO> {

        return try {
            val response: HttpResponse = client.get(
                urlString = HttpRouts.GET_PERSONAL_DATA
            ) {
                contentType(ContentType.Application.Json)
            }
            if (response.status != HttpStatusCode.OK) {
                Either.Left(Exception())
            }
            Either.Right(response.body<UserDTO>())
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(e)
        }
    }

    override suspend fun getRestaurants(): RestaurantsListDto {

        return client.get(
            urlString = HttpRouts.GET_RESTAURANTS
        ) {
            contentType(ContentType.Application.Json)
        }.body()
    }
}