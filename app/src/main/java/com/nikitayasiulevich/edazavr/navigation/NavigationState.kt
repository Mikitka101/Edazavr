package com.nikitayasiulevich.edazavr.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nikitayasiulevich.edazavr.domain.model.Restaurant

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToHomeGraph() {
        navHostController.navigate(Screen.HomeGraph.route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    fun navigateToLogin(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    fun navigateInMenu(route: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
        }
    }

    fun navigateToMenu(userId: String) {
        navHostController.navigate(Screen.Menu.getRouteWithArgs(userId)) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToPayment(orderId: String) {
        navHostController.navigate(Screen.Payment.getRouteWithArgs(orderId))
    }

    fun navigateToDishes(restaurantId: String) {
        navHostController.navigate(Screen.Dishes.getRouteWithArgs(restaurantId))
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}