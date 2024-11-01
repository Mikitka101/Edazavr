package com.nikitayasiulevich.edazavr.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileScreen(
    userId: String,
    onBackPressed: () -> Unit,
    onLogoutClickListener: () -> Unit)
{
    val viewModel: ProfileViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(ProfileScreenState.Initial)

    val currentState = screenState.value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { _ ->
        when (currentState) {
            is ProfileScreenState.UserInfo -> {
                Column(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxSize()
                ) {
                    Text(text = currentState.user.id, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = currentState.user.login, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = currentState.user.name, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        viewModel.logOut()
                        onLogoutClickListener()
                    }) {
                        Text(text = "Log out")
                    }
                }
            }

            is ProfileScreenState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.DarkGray)
                }
            }

            is ProfileScreenState.Initial -> {

            }
        }
    }
}