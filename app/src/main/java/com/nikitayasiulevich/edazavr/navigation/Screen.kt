package com.nikitayasiulevich.edazavr.navigation

import android.net.Uri
import com.google.gson.Gson

sealed class Screen(
    val route: String
) {

    data object Login : Screen(ROUTE_LOGIN)
    data object Register : Screen(ROUTE_REGISTER)
    data object Home : Screen(ROUTE_HOME)
    data object Menu : Screen(ROUTE_MENU)
    data object MenuGraph : Screen(ROUTE_MENU_GRAPH)
    data object HomeGraph : Screen(ROUTE_HOME_GRAPH)
    data object ShoppingCart : Screen(ROUTE_SHOPPING_CART)
    data object ShoppingCartGraph : Screen(ROUTE_SHOPPING_CART_GRAPH)
    data object Order : Screen(ROUTE_ORDER) {

        private const val ROUTE_FOR_ARGS = "order"

        fun getRouteWithArgs(orderDTO: com.nikitayasiulevich.edazavr.data.model.OrderDTO): String {
            val gson = Gson().toJson(orderDTO)
            return "$ROUTE_FOR_ARGS/${Uri.encode(gson)}"
        }
    }

    data object PreviousOrders : Screen(ROUTE_PREVIOUS_ORDERS)
    data object Bookmarks : Screen(ROUTE_BOOKMARKS)
    data object Addresses : Screen(ROUTE_ADDRESSES)
    data object Cards : Screen(ROUTE_CARDS)
    data object Courier : Screen(ROUTE_COURIER)
    data object Restaurant : Screen(ROUTE_RESTAURANT)


    companion object {

        const val ROUTE_LOGIN = "login"
        const val ROUTE_REGISTER = "register"

        const val ROUTE_HOME = "home"
        const val ROUTE_HOME_GRAPH = "home_graph"
        const val ROUTE_SHOPPING_CART = "shopping_cart"
        const val ROUTE_SHOPPING_CART_GRAPH = "shopping_cart_graph"

        const val KEY_ORDER = "photo"
        const val ROUTE_ORDER = "order/{${KEY_ORDER}}"
        const val ROUTE_MENU = "menu"
        const val ROUTE_MENU_GRAPH = "menu_graph"

        const val ROUTE_PREVIOUS_ORDERS = "previous_orders"
        const val ROUTE_BOOKMARKS = "bookmarks"
        const val ROUTE_ADDRESSES = "addresses"
        const val ROUTE_CARDS = "cards"
        const val ROUTE_COURIER = "courier"
        const val ROUTE_RESTAURANT = "restaurant"
    }
}