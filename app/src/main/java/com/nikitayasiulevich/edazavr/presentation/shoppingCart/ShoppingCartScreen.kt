package com.nikitayasiulevich.edazavr.presentation.shoppingCart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ShoppingCartScreen(
    onPayClickListener: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red)
    ) {
        Text(text = "ShoppingCartScreen")
        Button(onClick = {
            onPayClickListener( "orderId")
        }) {
            Text(text = "Pay")
        }
    }
}