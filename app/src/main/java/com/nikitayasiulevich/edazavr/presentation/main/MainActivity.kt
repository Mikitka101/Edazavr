package com.nikitayasiulevich.edazavr.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nikitayasiulevich.edazavr.ui.theme.EdazavrTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EdazavrTheme {
                MainScreen(application)
            }
        }
    }
}