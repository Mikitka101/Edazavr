package com.nikitayasiulevich.edazavr.presentation.profile

import com.nikitayasiulevich.edazavr.domain.model.User

sealed class ProfileScreenState {

    data object Initial : ProfileScreenState()

    data object Loading : ProfileScreenState()

    data class UserInfo(
        val user: User,
    ) : ProfileScreenState()
}