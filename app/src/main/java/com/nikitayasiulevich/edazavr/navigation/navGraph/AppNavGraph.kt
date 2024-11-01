package com.nikitayasiulevich.edazavr.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nikitayasiulevich.edazavr.navigation.Screen
import com.nikitayasiulevich.edazavr.navigation.Screen.Companion.KEY_USER_ID

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    isUserLoggedIn: Boolean,

    loginScreenContent: @Composable () -> Unit,
    registerScreenContent: @Composable () -> Unit,

    homeScreenContent: @Composable () -> Unit,
    menuScreenContent: @Composable (String) -> Unit,
    profileScreenContent: @Composable (String) -> Unit,
    /*ordersScreenContent: @Composable (String) -> Unit,
    bookmarksScreenContent: @Composable (String) -> Unit,
    addressesScreenContent: @Composable (String) -> Unit,
    cardsScreenContent: @Composable (String) -> Unit,*/


    shoppingCartScreenContent: @Composable () -> Unit,
    paymentScreenContent: @Composable (String) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = if (isUserLoggedIn) Screen.HomeGraph.route else Screen.AuthGraph.route,
    ) {
        loginScreenNavGraph(
            loginScreenContent = loginScreenContent,
            registerScreenContent = registerScreenContent,
        )
        homeScreenNavGraph(
            homeScreenContent = homeScreenContent,
            menuScreenContent = menuScreenContent
        )
        shoppingCartScreenNavGraph(
            shoppingCartScreenContent = shoppingCartScreenContent,
            paymentScreenContent = paymentScreenContent
        )
        composable(route = Screen.Profile.route, arguments = listOf(
            navArgument(KEY_USER_ID) {
                type = NavType.StringType
            }
        )) {
            val userId = it.arguments?.getString(KEY_USER_ID) ?: ""
            profileScreenContent(userId)
        }
    }
}