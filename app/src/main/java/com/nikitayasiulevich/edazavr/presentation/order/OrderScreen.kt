package com.nikitayasiulevich.edazavr.presentation.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikitayasiulevich.edazavr.data.model.OrderDTO
import com.nikitayasiulevich.edazavr.ui.theme.LightGray

@Composable
fun OrderScreen(
    orderDTO: OrderDTO,
    onBackPressed: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Order: ${orderDTO.id}")
        FloatingActionButton(
            modifier = Modifier
                .size(50.dp),
            shape = RoundedCornerShape(15.dp),
            containerColor = LightGray,
            onClick = {
                onBackPressed()
            }
        ) {
            Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back button"
            )
            Row(
                modifier = Modifier
                    .padding(end = 50.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = orderDTO.id.toString(),
                    fontSize = 22.sp
                )
            }
        }
    }
}