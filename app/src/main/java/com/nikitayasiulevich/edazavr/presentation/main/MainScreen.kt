package com.nikitayasiulevich.edazavr.presentation.main

import android.app.Application
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nikitayasiulevich.edazavr.navigation.AppNavGraph
import com.nikitayasiulevich.edazavr.navigation.Screen
import com.nikitayasiulevich.edazavr.navigation.rememberNavigationState
import com.nikitayasiulevich.edazavr.presentation.auth.LoginScreen
import com.nikitayasiulevich.edazavr.presentation.commons.NavigationItem
import com.nikitayasiulevich.edazavr.presentation.home.HomeScreen
import com.nikitayasiulevich.edazavr.presentation.menu.MenuScreen
import com.nikitayasiulevich.edazavr.presentation.order.OrderScreen
import com.nikitayasiulevich.edazavr.presentation.shoppingCart.ShoppingCartScreen
import com.nikitayasiulevich.edazavr.ui.theme.Gray
import com.nikitayasiulevich.edazavr.ui.theme.Red40
import com.nikitayasiulevich.edazavr.ui.theme.WhiteEE
import com.nikitayasiulevich.edazavr.ui.theme.WhiteFF

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
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            isUserLogIn = application.getSharedPreferences(
                "com.nikitayasiulevich.edazavr.tokens",
                Context.MODE_PRIVATE
            ).getBoolean("is_auth", false),
            loginScreenContent = {
                bottomBarState.value = false
                LoginScreen(application) { navigationState.navigateTo(Screen.Home.route) }
            },
            homeScreenContent = {
                bottomBarState.value = true
                HomeScreen(
                    paddingValues = paddingValues,
                    onMenuClickListener = { navigationState.navigateToMenu() }
                )
            },
            shoppingCartScreenContent = {
                bottomBarState.value = true
                ShoppingCartScreen(
                    paddingValues = paddingValues,
                    onOrderClickListener = { order ->
                        navigationState.navigateToOrder(order)
                    }
                )
            },
            menuScreenContent = {
                bottomBarState.value = false
                MenuScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    onItemClickListener = { navItem ->
                        navigationState.navigateInMenu(navItem.screen.route)
                    },
                    menuItems = listOf(
                        NavigationItem.PreviousOrders,
                        NavigationItem.Bookmarks,
                        NavigationItem.Addresses,
                        NavigationItem.Cards,
                        NavigationItem.Courier,
                        NavigationItem.Restaurant
                    )
                )
            },
            previousOrdersScreenContent = {
                Text(text = "previousOrdersScreenContent")
            },
            bookmarksScreenContent = {},
            addressesScreenContent = {},
            cardsScreenContent = {},
            courierScreenContent = {},
            restaurantScreenContent = {},
            orderScreenContent = { order ->
                bottomBarState.value = false
                OrderScreen(
                    orderDTO = order,
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    }
                )
            }
        )
    }
}