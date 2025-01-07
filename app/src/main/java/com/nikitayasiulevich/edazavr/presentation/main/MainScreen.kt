package com.nikitayasiulevich.edazavr.presentation.main

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.nikitayasiulevich.edazavr.data.FileReader
import com.nikitayasiulevich.edazavr.data.remote.HttpClient
import com.nikitayasiulevich.edazavr.data.repository.FileRepository
import com.nikitayasiulevich.edazavr.navigation.Screen
import com.nikitayasiulevich.edazavr.navigation.navGraph.AppNavGraph
import com.nikitayasiulevich.edazavr.navigation.rememberNavigationState
import com.nikitayasiulevich.edazavr.presentation.auth.LoginScreen
import com.nikitayasiulevich.edazavr.presentation.auth.RegisterScreen
import com.nikitayasiulevich.edazavr.presentation.commons.NavigationItem
import com.nikitayasiulevich.edazavr.presentation.createRestaurant.CreateRestaurantViewModel
import com.nikitayasiulevich.edazavr.presentation.createRestaurant.UploadState
import com.nikitayasiulevich.edazavr.presentation.dishes.DishesScreen
import com.nikitayasiulevich.edazavr.presentation.home.HomeScreen
import com.nikitayasiulevich.edazavr.presentation.menu.MenuScreen
import com.nikitayasiulevich.edazavr.presentation.profile.ProfileScreen
import com.nikitayasiulevich.edazavr.presentation.shoppingCart.ShoppingCartScreen
import com.nikitayasiulevich.edazavr.ui.theme.Gray
import com.nikitayasiulevich.edazavr.ui.theme.Red40
import com.nikitayasiulevich.edazavr.ui.theme.WhiteEE
import com.nikitayasiulevich.edazavr.ui.theme.WhiteFF
import com.nikitayasiulevich.edazavr.utils.Constants
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun MainScreen(
    application: Application
) {

    val navigationState = rememberNavigationState()

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        containerColor = WhiteEE,
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomAppBar(
                    modifier = Modifier.height(56.dp),
                    containerColor = WhiteFF,
                    tonalElevation = 8.dp
                ) {
                    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                    val items = listOf(
                        NavigationItem.Home,
                        NavigationItem.ShoppingCart
                    )

                    items.forEach { item ->
                        val selected = navBackStackEntry?.destination?.hierarchy?.any {
                            it.route == item.screen.route
                        } ?: false
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (!selected) {
                                    navigationState.navigateTo(item.screen.route)
                                }
                            },
                            icon = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(top = 0.dp, bottom = 8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Spacer(
                                        modifier = Modifier
                                            .width(50.dp)
                                            .height(3.dp)
                                            .background(color = if (selected) Red40 else Color.White)
                                    )
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = stringResource(id = item.titleResId),
                                        modifier = Modifier
                                            .padding(bottom = 4.dp)
                                            .size(30.dp)
                                    )
                                }
                            },
                            colors = NavigationBarItemColors(
                                selectedIconColor = Red40,
                                unselectedIconColor = Gray,
                                selectedIndicatorColor = Color.Transparent,
                                selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                                disabledIconColor = MaterialTheme.colorScheme.onSecondary,
                                disabledTextColor = MaterialTheme.colorScheme.onSecondary
                            )
                        )
                    }
                }
            }
        }
    ) { _ ->
        AppNavGraph(
            navHostController = navigationState.navHostController,

            isUserLoggedIn = application.getSharedPreferences(
                Constants.SHARED_PREF_NAME,
                Context.MODE_PRIVATE
            ).getBoolean("is_auth", false),

            loginScreenContent = {
                bottomBarState.value = false
                LoginScreen { navigationState.navigateToHomeGraph() }
            },
            registerScreenContent = {
                bottomBarState.value = false
                RegisterScreen(application) { navigationState.navigateTo(Screen.HomeGraph.route) }
            },
            homeScreenContent = {
                bottomBarState.value = true
                HomeScreen(
                    onMenuClickListener = { userId -> navigationState.navigateToMenu(userId) },
                    onRestaurantClickListener = { restaurant ->
                        navigationState.navigateToDishes(restaurant.id)
                    }
                )
            },
            menuScreenContent = { userId ->
                bottomBarState.value = false
                MenuScreen(
                    userId = userId,
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    onItemClickListener = { navItem ->
                        navigationState.navigateInMenu(navItem.screen.route)
                    },
                    menuItems = listOf(
                        NavigationItem.Profile,
                        NavigationItem.Orders,
                        NavigationItem.Bookmarks,
                        NavigationItem.Addresses,
                        NavigationItem.Cards,
                        NavigationItem.Courier,
                        NavigationItem.Restaurant
                    )
                )
            },
            dishesScreenContent = { restaurantId ->
                bottomBarState.value = false
                DishesScreen(
                    restaurantId = restaurantId,
                    application = application,
                    onMenuClickListener = {}
                )
            },
            profileScreenContent = { userId ->
                ProfileScreen(
                    userId = userId,
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    onLogoutClickListener = {
                        navigationState.navigateToLogin(Screen.AuthGraph.route)
                    }
                )
            },
            shoppingCartScreenContent = {
                bottomBarState.value = true
                ShoppingCartScreen(
                    user = Random.nextInt(),
                    onPayClickListener = { order ->
                        navigationState.navigateToPayment(order)
                    }
                )
            },
            paymentScreenContent = { userId ->
                Text(text = userId)
            },
            ordersScreenContent = {
                var viewModel: CreateRestaurantViewModel = viewModel {
                    CreateRestaurantViewModel(
                        repository = FileRepository(
                            application = application,
                            fileReader = FileReader(context = application)
                        )
                    )
                }
                bottomBarState.value = false
                val context = application
                val state = viewModel.state.collectAsState()

                val filePickerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { contentUri ->
                    contentUri?.let {
                        viewModel.uploadFile(contentUri)
                    }
                }


                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(25.dp)
                    )

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (val currentState = state.value) {
                            UploadState.Initial -> {
                                Button(onClick = { filePickerLauncher.launch("image/*") }) {
                                    Text(text = "Pick a file")
                                }
                            }

                            UploadState.Loading -> {
                                CircularProgressIndicator()
                            }

                            is UploadState.Success -> {
                                Column(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    AsyncImage(
                                        model = currentState.response.url,
                                        modifier = Modifier
                                            .height(300.dp),
                                        contentDescription = currentState.response.id,
                                        contentScale = ContentScale.Crop,
                                    )
                                    Text("Upload successful! ID: ${currentState.response.id}, URL: ${currentState.response.url}")
                                }
                            }

                            is UploadState.Error -> {
                                Text("Upload failed: ${currentState.message}")
                                Toast.makeText(
                                    context,
                                    "Upload failed: ${currentState.message}",
                                    Toast.LENGTH_LONG
                                ).show()

                            }

                            else -> {}
                        }
                    }
                }
            }
        )
    }
}