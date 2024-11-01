package com.nikitayasiulevich.edazavr.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nikitayasiulevich.edazavr.navigation.Screen

fun NavGraphBuilder.loginScreenNavGraph(
    loginScreenContent: @Composable () -> Unit,
    registerScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.Login.route,
        route = Screen.AuthGraph.route,
    ) {
        composable(route = Screen.Login.route) {
            loginScreenContent()
        }
        composable(route = Screen.Register.route) {
            registerScreenContent()
        }
    }
}