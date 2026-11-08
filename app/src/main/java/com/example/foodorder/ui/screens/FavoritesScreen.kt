package com.example.foodorder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodorder.data.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FoodViewModel,
    navigateToDetail: (String) -> Unit
) {
    val foods by viewModel.foods.collectAsState()
    // Dummy: hanya mengambil 2 data pertama sebagai menu favorit
    val favoriteFoods = foods.take(2)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Favorit Saya", fontWeight = FontWeight.Bold) })
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(favoriteFoods) { food ->
                FoodCard(food = food, onClick = { navigateToDetail(food.id) })
            }
        }
    }
}
