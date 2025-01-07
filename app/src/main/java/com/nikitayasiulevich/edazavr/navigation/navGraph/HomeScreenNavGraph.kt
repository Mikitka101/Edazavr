package com.nikitayasiulevich.edazavr.navigation.navGraph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.nikitayasiulevich.edazavr.navigation.Screen
import com.nikitayasiulevich.edazavr.navigation.Screen.Companion.KEY_RESTAURANT_ID
import com.nikitayasiulevich.edazavr.navigation.Screen.Companion.KEY_USER_ID

fun NavGraphBuilder.homeScreenNavGraph(
    homeScreenContent: @Composable () -> Unit,
    menuScreenContent: @Composable (String) -> Unit,
    dishesScreenContent: @Composable (String) -> Unit,
    ordersScreenContent: @Composable () -> Unit
) {
    navigation(
        startDestination = Screen.Home.route,
        route = Screen.HomeGraph.route
    ) {
        composable(route = Screen.Home.route,
            enterTransition = {
                slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }) {
            homeScreenContent()
        }
        composable(route = Screen.Menu.route,
            arguments = listOf(
                navArgument(KEY_USER_ID) {
                    type = NavType.StringType
                }
            )) {
            val userId = it.arguments?.getString(KEY_USER_ID) ?: ""
            menuScreenContent(userId)
        }
        composable(route = Screen.Dishes.route,
            arguments = listOf(
                navArgument(KEY_RESTAURANT_ID) {
                    type = NavType.StringType
                }
            )) {
            val restaurantId = it.arguments?.getString(KEY_RESTAURANT_ID) ?: ""
            dishesScreenContent(restaurantId)
        }
        composable(route = Screen.Orders.route) {
            ordersScreenContent()
        }
    }
}

