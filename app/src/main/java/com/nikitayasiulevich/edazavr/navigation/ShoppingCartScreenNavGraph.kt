package com.nikitayasiulevich.edazavr.navigation

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
import com.google.gson.Gson
import com.nikitayasiulevich.edazavr.data.model.OrderDTO
import com.nikitayasiulevich.edazavr.navigation.Screen.Companion.KEY_ORDER

fun NavGraphBuilder.shoppingCartScreenNavGraph(
    shoppingCartScreenContent: @Composable () -> Unit,
    orderScreenContent: @Composable (OrderDTO) -> Unit
) {
    navigation(
        startDestination = Screen.ShoppingCart.route,
        route = Screen.ShoppingCartGraph.route
    ) {
        composable(
            route = Screen.ShoppingCart.route,
            enterTransition = {
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
            }
        ) {
            shoppingCartScreenContent()
        }
        composable(
            route = Screen.Order.route,
            arguments = listOf(
                navArgument(KEY_ORDER) {
                    type = NavType.StringType
                }
            )
        ) {
            val orderJson = it.arguments?.getString(KEY_ORDER) ?: ""
            val orderDTO = Gson().fromJson(orderJson, OrderDTO::class.java)
            orderScreenContent(orderDTO)
        }
    }
}