package com.nikitayasiulevich.edazavr.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nikitayasiulevich.edazavr.data.model.OrderDTO

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
    fun navigateInMenu(route: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToMenu() {
        navHostController.navigate(Screen.Menu.route)
    }

    fun navigateToOrder(orderDTO: OrderDTO) {
        navHostController.navigate(Screen.Order.getRouteWithArgs(orderDTO))
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