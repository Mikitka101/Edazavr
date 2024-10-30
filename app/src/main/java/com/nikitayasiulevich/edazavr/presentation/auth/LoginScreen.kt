package com.nikitayasiulevich.edazavr.presentation.auth

import android.app.Application
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    application: Application,
    onLogin: () -> Unit
) {
    val sharedPreferences = application.getSharedPreferences(
        "com.nikitayasiulevich.edazavr.tokens",
        Context.MODE_PRIVATE
    )
    if(sharedPreferences.getBoolean("is_auth", false)) {
        onLogin()
    }

    val viewModel = LoginViewModel(application)

    val tokens = viewModel.tokens.observeAsState()

    var login by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        tokens.value?.let { Text(text = it.accessToken) }
        tokens.value?.let { Text(text = it.refreshToken) }
        TextField(
            value = login,
            textStyle = TextStyle(fontSize = 25.sp),
            onValueChange = { newText -> login = newText }
        )

        TextField(
            value = password,
            textStyle = TextStyle(fontSize = 25.sp),
            onValueChange = { newText -> password = newText }
        )

        Button(onClick = { viewModel.login(login, password) }) {
            Text(text = "login")
        }

    }
}