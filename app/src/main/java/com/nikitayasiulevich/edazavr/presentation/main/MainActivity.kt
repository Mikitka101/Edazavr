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




/*private lateinit var service: ApiService*/
/*service = ApiService.create(this)
val tokens = produceState<AuthResponse>(
    initialValue = AuthResponse("", "")
) {
    value = service.authorize(this@MainActivity)
}

EdazavrTheme {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Access: ${tokens.value.accessToken}",
                fontSize = 5.sp,
                modifier = Modifier.padding(innerPadding)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            )
            Text(
                text = "Refresh: ${tokens.value.refreshToken}",
                fontSize = 5.sp,
                modifier = Modifier.padding(innerPadding)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
            )

            val viewModel = viewModel {
                CreateRestaurantViewModel(
                    repository = FileRepository(
                        client = HttpClient.client,
                        fileReader = FileReader(
                            context = applicationContext
                        )
                    )
                )
            }

            val state = viewModel.state

            val filePickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { contentUri ->
                contentUri?.let {
                    viewModel.uploadFile(contentUri)
                }
            }

            LaunchedEffect(key1 = state.errorMessage) {
                state.errorMessage?.let {
                    Toast.makeText(
                        applicationContext,
                        state.errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            LaunchedEffect(key1 = state.isUploadComplete) {
                if (state.isUploadComplete) {
                    Toast.makeText(
                        applicationContext,
                        "Upload complete!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                when {
                    !state.isUploading -> {
                        Button(onClick = {
                            filePickerLauncher.launch("image/*")
                        }) {
                            Text(text = "Pick a file")
                        }
                    }

                    else -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val animatedProgress by animateFloatAsState(
                                targetValue = state.progress,
                                animationSpec = tween(durationMillis = 100),
                                label = "File upload progress bar"
                            )
                            LinearProgressIndicator(
                                progress = { animatedProgress },
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                                    .height(16.dp)
                            )
                            Text(
                                text = "${(state.progress * 100).roundToInt()}%"
                            )
                            Button(onClick = {
                                viewModel.cancelUpload()
                            }) {
                                Text(text = "Cancel upload")
                            }
                        }
                    }
                }
            }
        }
    }*/
*/