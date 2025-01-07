package com.nikitayasiulevich.edazavr.navigation

import android.net.Uri

sealed class Screen(
    val route: String
) {

    data object Login : Screen(ROUTE_LOGIN)
    data object Register : Screen(ROUTE_REGISTER)
    data object AuthGraph : Screen(ROUTE_AUTH_GRAPH)

    data object Home : Screen(ROUTE_HOME)
    data object HomeGraph : Screen(ROUTE_HOME_GRAPH)
    data object Menu : Screen(ROUTE_MENU) {
        private const val ROUTE_FOR_ARGS = "menu"

        fun getRouteWithArgs(userId: String): String {
            return "$ROUTE_FOR_ARGS/${Uri.encode(userId)}"
        }
    }
    data object Dishes : Screen(ROUTE_DISHES) {
        private const val ROUTE_FOR_ARGS = "restaurant"

        fun getRouteWithArgs(restaurantId: String): String {
            return "$ROUTE_FOR_ARGS/${Uri.encode(restaurantId)}"
        }
    }

    data object ShoppingCart : Screen(ROUTE_SHOPPING_CART)
    data object ShoppingCartGraph : Screen(ROUTE_SHOPPING_CART_GRAPH)
    data object Payment : Screen(ROUTE_PAYMENT) {

        private const val ROUTE_FOR_ARGS = "payment"

        fun getRouteWithArgs(orderId: String): String {
            return "$ROUTE_FOR_ARGS/${Uri.encode(orderId)}"
        }
    }

    data object Profile : Screen(ROUTE_PROFILE)
    data object Orders : Screen(ROUTE_ORDERS)
    data object Bookmarks : Screen(ROUTE_BOOKMARKS)
    data object Addresses : Screen(ROUTE_ADDRESSES)
    data object Cards : Screen(ROUTE_CARDS)
    data object Courier : Screen(ROUTE_COURIER)
    data object Restaurant : Screen(ROUTE_RESTAURANT)


    companion object {

        private const val ROUTE_LOGIN = "login"
        private const val ROUTE_AUTH_GRAPH = "login_graph"
        private const val ROUTE_REGISTER = "register"

        private const val ROUTE_HOME = "home"
        private const val ROUTE_HOME_GRAPH = "home_graph"
        const val KEY_USER_ID = "user"
        private const val ROUTE_MENU = "menu/{${KEY_USER_ID}}"
        const val KEY_RESTAURANT_ID = "restaurant"
        private const val ROUTE_DISHES = "restaurant/{${KEY_RESTAURANT_ID}}"


        private const val ROUTE_SHOPPING_CART = "shopping_cart"
        private const val ROUTE_SHOPPING_CART_GRAPH = "shopping_cart_graph"
        const val KEY_ORDER_ID = "order"
        private const val ROUTE_PAYMENT = "payment/{${KEY_ORDER_ID}}"

        private const val ROUTE_PROFILE = "profile/{${KEY_USER_ID}}"
        private const val ROUTE_ORDERS = "orders"
        private const val ROUTE_BOOKMARKS = "bookmarks"
        private const val ROUTE_ADDRESSES = "addresses"
        private const val ROUTE_CARDS = "cards"
        private const val ROUTE_COURIER = "courier"
        private const val ROUTE_RESTAURANT = "restaurant"
    }
}