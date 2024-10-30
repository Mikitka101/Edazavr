package com.nikitayasiulevich.edazavr.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    isUserLogIn: Boolean,
    loginScreenContent: @Composable () -> Unit,
    homeScreenContent: @Composable () -> Unit,
    menuScreenContent: @Composable () -> Unit
) {
    navigation(
        startDestination = if (isUserLogIn) Screen.Home.route else Screen.Login.route,
        route = Screen.HomeGraph.route,
    ) {
        composable(route = Screen.Login.route) {
            loginScreenContent()
        }
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
        composable(route = Screen.Menu.route) {
            menuScreenContent()
        }
    }
}