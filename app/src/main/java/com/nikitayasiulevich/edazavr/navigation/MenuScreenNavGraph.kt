package com.nikitayasiulevich.edazavr.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nikitayasiulevich.edazavr.presentation.commons.NavigationItem

fun NavGraphBuilder.menuScreenNavGraph(
    menuScreenContent: @Composable () -> Unit,
    previousOrdersScreenContent: @Composable () -> Unit,
    bookmarksScreenContent: @Composable () -> Unit,
    addressesScreenContent: @Composable () -> Unit,
    cardsScreenContent: @Composable () -> Unit,
    courierScreenContent: @Composable () -> Unit,
    restaurantScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.Menu.route,
        route = Screen.MenuGraph.route
    ) {
        composable(route = Screen.Menu.route,
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
            }) {
            menuScreenContent()
        }
        composable(route = Screen.PreviousOrders.route) {
            previousOrdersScreenContent()
        }
        composable(route = Screen.Bookmarks.route) {
            bookmarksScreenContent()
        }
        composable(route = Screen.Addresses.route) {
            addressesScreenContent()
        }
        composable(route = Screen.Cards.route) {
            cardsScreenContent()
        }
        composable(route = Screen.Courier.route) {
            courierScreenContent()
        }
        composable(route = Screen.Restaurant.route) {
            restaurantScreenContent()
        }
    }
}