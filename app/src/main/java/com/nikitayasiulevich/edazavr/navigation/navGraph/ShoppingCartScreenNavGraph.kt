package com.nikitayasiulevich.edazavr.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.nikitayasiulevich.edazavr.navigation.Screen
import com.nikitayasiulevich.edazavr.navigation.Screen.Companion.KEY_ORDER_ID

fun NavGraphBuilder.shoppingCartScreenNavGraph(
    shoppingCartScreenContent: @Composable () -> Unit,
    paymentScreenContent: @Composable (String) -> Unit
) {
    navigation(
        startDestination = Screen.ShoppingCart.route,
        route = Screen.ShoppingCartGraph.route
    ) {
        composable(route = Screen.ShoppingCart.route,/* enterTransition = {
            slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
            exitTransition = {
                slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }*/) {
            shoppingCartScreenContent()
        }
        composable(route = Screen.Payment.route,
            arguments = listOf(
                navArgument(KEY_ORDER_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val orderId = it.arguments?.getString(KEY_ORDER_ID) ?: ""
            paymentScreenContent(orderId)
        }
    }
}

