package com.example.foodorder.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodorder.data.FoodViewModel
import com.example.foodorder.ui.screens.*

@Composable
fun FoodOrderApp(viewModel: FoodViewModel = viewModel()) {
    val navController = rememberNavController()
    
    val items = listOf(
        Triple("home", "Beranda", Icons.Default.Home),
        Triple("orders", "Pesanan", Icons.AutoMirrored.Filled.List),
        Triple("favorites", "Favorit", Icons.Default.FavoriteBorder),
        Triple("profile", "Profil", Icons.Default.Person)
    )

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            
            // Only show bottom bar on root screens
            if (currentDestination?.route in items.map { it.first }) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    items.forEach { (route, label, icon) ->
                        NavigationBarItem(
                            icon = { Icon(icon, contentDescription = label) },
                            label = { Text(label) },
                            selected = currentDestination?.hierarchy?.any { it.route == route } == true,
                            onClick = {
                                navController.navigate(route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                                selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                                indicatorColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController, 
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = viewModel,
                    navigateToDetail = { foodId -> navController.navigate(Screen.Detail.createRoute(foodId)) },
                    navigateToCart = { navController.navigate(Screen.Cart.route) },
                    navigateToTheme = { navController.navigate(Screen.Theme.route) }
                )
            }
            composable(Screen.Detail.route) { backStackEntry ->
                val foodId = backStackEntry.arguments?.getString("foodId") ?: ""
                FoodDetailScreen(
                    foodId = foodId,
                    viewModel = viewModel,
                    navigateBack = { navController.popBackStack() },
                    navigateToCart = { navController.navigate(Screen.Cart.route) }
                )
            }
            composable(Screen.Cart.route) {
                CartScreen(
                    viewModel = viewModel,
                    navigateBack = { navController.popBackStack() },
                    navigateToCheckout = { navController.navigate(Screen.Checkout.route) }
                )
            }
            composable(Screen.Checkout.route) {
                CheckoutScreen(
                    viewModel = viewModel,
                    navigateBack = { navController.popBackStack() },
                    navigateHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Theme.route) {
                ThemeScreen(
                    viewModel = viewModel,
                    navigateBack = { navController.popBackStack() }
                )
            }
            
            // Placeholder for other bottom nav items
            composable("favorites") { 
                FavoritesScreen(
                    viewModel = viewModel,
                    navigateToDetail = { foodId -> navController.navigate(Screen.Detail.createRoute(foodId)) }
                ) 
            }
            composable("orders") { OrdersScreen() }
            composable("profile") { ProfileScreen(viewModel) }
        }
    }
}
