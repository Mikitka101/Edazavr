package com.nikitayasiulevich.edazavr.data.remote

object HttpRouts {

    private const val BASE_URL = "http://10.0.2.2:8080/api/v1"

    const val AUTH = "$BASE_URL/auth"
    const val REFRESH = "$BASE_URL/auth/refresh"
    const val GET_PERSONAL_DATA = "$BASE_URL/users/get-personal-data"
    const val GET_RESTAURANTS = "$BASE_URL/restaurants"
    const val GET_RESTAURANT = "$BASE_URL/restaurants/restaurant-info"
    const val GET_DISHES = "$BASE_URL/dishes/get-restaurant-dishes"
    const val UPLOAD_IMAGE = "$BASE_URL/images/upload-image"
}