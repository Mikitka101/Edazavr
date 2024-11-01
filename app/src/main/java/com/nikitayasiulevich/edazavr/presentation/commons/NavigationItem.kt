package com.nikitayasiulevich.edazavr.presentation.commons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.twotone.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import com.nikitayasiulevich.edazavr.R
import com.nikitayasiulevich.edazavr.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {
    //Bottom bar items

    data object Home : NavigationItem(
        screen = Screen.HomeGraph,
        titleResId = R.string.navigation_item_home,
        icon = Icons.Outlined.Home
    )

    data object ShoppingCart : NavigationItem(
        screen = Screen.ShoppingCartGraph,
        titleResId = R.string.navigation_item_shopping_cart,
        icon = Icons.Outlined.ShoppingCart
    )

    //Menu Items

    data object Profile : NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.navigation_item_profile,
        icon = Icons.TwoTone.Warning
    )

    data object Orders : NavigationItem(
        screen = Screen.Orders,
        titleResId =R.string.navigation_item_orders,
        icon = Icons.TwoTone.Warning
    )

    data object Bookmarks : NavigationItem(
        screen = Screen.Bookmarks,
        titleResId = R.string.navigation_item_bookmarks,
        icon = Icons.Outlined.Favorite
    )

    data object Addresses : NavigationItem(
        screen = Screen.Addresses,
        titleResId = R.string.navigation_item_addresses,
        icon = Icons.Outlined.Home
    )

    data object Cards : NavigationItem(
        screen = Screen.Cards,
        titleResId = R.string.navigation_item_cards,
        icon = Icons.Outlined.Star
    )

    data object Courier : NavigationItem(
        screen = Screen.Courier,
        titleResId = R.string.navigation_item_courier,
        icon = Icons.Outlined.Home
    )

    data object Restaurant : NavigationItem(
        screen = Screen.Restaurant,
        titleResId = R.string.navigation_item_restaurant,
        icon = Icons.Outlined.ShoppingCart
    )
}