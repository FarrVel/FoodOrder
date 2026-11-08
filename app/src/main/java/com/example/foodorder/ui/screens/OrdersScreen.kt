package com.example.foodorder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pesanan Saya", fontWeight = FontWeight.Bold) })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { 
                OrderCard(
                    orderId = "#10294", 
                    status = "Sedang Diproses", 
                    items = "2x Burger Premium, 1x Coca Cola Zero", 
                    total = "Rp125.000", 
                    isOngoing = true
                ) 
            }
            item { 
                OrderCard(
                    orderId = "#10293", 
                    status = "Selesai", 
                    items = "1x Pizza Margherita", 
                    total = "Rp60.000", 
                    isOngoing = false
                ) 
            }
            item { 
                OrderCard(
                    orderId = "#10280", 
                    status = "Selesai", 
                    items = "2x Sushi Mix", 
                    total = "Rp160.000", 
                    isOngoing = false
                ) 
            }
        }
    }
}

@Composable
fun OrderCard(orderId: String, status: String, items: String, total: String, isOngoing: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(orderId, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = status, 
                    color = if (isOngoing) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(items, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", style = MaterialTheme.typography.bodyMedium)
                Text(total, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}
