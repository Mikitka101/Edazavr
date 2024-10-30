package com.nikitayasiulevich.edazavr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nikitayasiulevich.edazavr.data.model.OrderDTO


@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    isUserLoggedIn: Boolean,
    loginScreenContent: @Composable () -> Unit,
    registerScreenContent: @Composable () -> Unit,
    homeScreenContent: @Composable () -> Unit,
    menuScreenContent: @Composable () -> Unit,
    previousOrdersScreenContent: @Composable () -> Unit,
    bookmarksScreenContent: @Composable () -> Unit,
    addressesScreenContent: @Composable () -> Unit,
    cardsScreenContent: @Composable () -> Unit,
    courierScreenContent: @Composable () -> Unit,
    restaurantScreenContent: @Composable () -> Unit,
    shoppingCartScreenContent: @Composable () -> Unit,
    orderScreenContent: @Composable (OrderDTO) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeGraph.route,
    ) {
        homeScreenNavGraph(
            isUserLogIn = isUserLogIn,
            loginScreenContent = loginScreenContent,
            homeScreenContent = homeScreenContent,
            menuScreenContent = menuScreenContent
        )
        shoppingCartScreenNavGraph(
            shoppingCartScreenContent = shoppingCartScreenContent,
            orderScreenContent = orderScreenContent
        )
        menuScreenNavGraph(
            menuScreenContent = menuScreenContent,
            previousOrdersScreenContent = previousOrdersScreenContent,
            bookmarksScreenContent = bookmarksScreenContent,
            addressesScreenContent = addressesScreenContent,
            cardsScreenContent = cardsScreenContent,
            courierScreenContent = courierScreenContent,
            restaurantScreenContent = restaurantScreenContent
        )
    }
}