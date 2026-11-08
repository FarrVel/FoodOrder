package com.example.foodorder.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{foodId}") {
        fun createRoute(foodId: String) = "detail/$foodId"
    }
    object Cart : Screen("cart")
    object Checkout : Screen("checkout")
    object Theme : Screen("theme")
}
