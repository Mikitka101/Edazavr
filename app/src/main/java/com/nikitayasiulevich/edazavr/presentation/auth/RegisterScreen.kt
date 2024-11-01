package com.nikitayasiulevich.edazavr.presentation.auth

import android.app.Application
import androidx.compose.runtime.Composable

@Composable
fun RegisterScreen(
    application: Application,
    onLoginSucceed: (String) -> Unit
) {
    /*val sharedPreferences = application.getSharedPreferences(
        "com.nikitayasiulevich.edazavr.tokens",
        Context.MODE_PRIVATE
    )

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

    }*/
}