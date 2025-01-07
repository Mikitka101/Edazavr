package com.nikitayasiulevich.edazavr.data.remote

import android.content.Context
import androidx.core.app.PendingIntentCompat.send
import arrow.core.Either
import com.mikitayasiulevich.data.model.requests.IdRequest
import com.nikitayasiulevich.edazavr.data.FileInfo
import com.nikitayasiulevich.edazavr.data.model.DishesListDto
import com.nikitayasiulevich.edazavr.data.model.RestaurantDto
import com.nikitayasiulevich.edazavr.data.model.RestaurantsListDto
import com.nikitayasiulevich.edazavr.data.remote.request.LoginRequest
import com.nikitayasiulevich.edazavr.data.remote.response.AuthResponse
import com.nikitayasiulevich.edazavr.data.model.UserDTO
import com.nikitayasiulevich.edazavr.data.remote.response.ImageUploadResponse
import com.nikitayasiulevich.edazavr.data.repository.ProgressUpdate
import com.nikitayasiulevich.edazavr.domain.model.Restaurant
import com.nikitayasiulevich.edazavr.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json


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

    override suspend fun getRestaurant(restaurantId: String): RestaurantDto {

        return client.post(
            urlString = HttpRouts.GET_RESTAURANT
        ) {
            contentType(ContentType.Application.Json)
            setBody(IdRequest(id = restaurantId))
        }.body()
    }

    override suspend fun getDishes(restaurantId: String): DishesListDto {

        return client.post(
            urlString = HttpRouts.GET_DISHES
        ) {
            contentType(ContentType.Application.Json)
            setBody(IdRequest(id = restaurantId))
        }.body()
    }

    override suspend fun uploadImage(fileInfo: FileInfo, onUpload: (Long, Long?) -> Unit ): ImageUploadResponse? {
        try {
            val response: HttpResponse = client.submitFormWithBinaryData(
                url = HttpRouts.UPLOAD_IMAGE,
                formData = formData {
                    append(
                        "image",
                        fileInfo.bytes,
                        Headers.build {
                            append(HttpHeaders.ContentType, fileInfo.mimeType)
                            append(HttpHeaders.ContentDisposition, "filename = ${fileInfo.name}")
                        }
                    )
                }
            ) {
                onUpload { bytesSentTotal, totalBytes ->
                    onUpload(bytesSentTotal, totalBytes)
                }
            }
            if (response.status.isSuccess()) {
                val json = Json { ignoreUnknownKeys = true }
                return json.decodeFromString<ImageUploadResponse>(response.bodyAsText())
            } else {
                println("Ошибка загрузки: ${response.status}")
            }

        } catch (e: Exception) {
            println("Ошибка: ${e.message}")
        }
        return null
    }
}