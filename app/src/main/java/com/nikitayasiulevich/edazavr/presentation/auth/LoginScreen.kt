package com.nikitayasiulevich.edazavr.presentation.auth

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
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    onLoginSucceed: () -> Unit
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel: LoginViewModel = viewModel()
    val isAuthorized = viewModel.isAuthorized.observeAsState(initial = false)
    val showError = viewModel.showError.observeAsState(initial = false)
    val currentErrorState = showError.value

    if (isAuthorized.value) {
        onLoginSucceed()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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

        if (currentErrorState) {
            Text(
                text = "Authorization error",
            )
        }
    }
}