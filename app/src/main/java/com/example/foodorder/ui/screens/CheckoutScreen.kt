package com.example.foodorder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodorder.data.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    viewModel: FoodViewModel,
    navigateBack: () -> Unit,
    navigateHome: () -> Unit
) {
    val cartItems by viewModel.cartItems.collectAsState()
    val subtotal = cartItems.sumOf { it.food.price * it.quantity }
    val ongkir = if (cartItems.isEmpty()) 0 else 5000
    val total = subtotal + ongkir
    
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Surface(tonalElevation = 8.dp) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Buat Pesanan")
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("Alamat Pengiriman", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    Text("Jl. Pendidikan No. 10 Sidoarjo", modifier = Modifier.padding(16.dp))
                }
            }
            
            item {
                Text("Metode Pembayaran", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row {
                            RadioButton(selected = true, onClick = null)
                            Text("Transfer Bank", modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }
            
            item {
                Text("Ringkasan Pesanan", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        cartItems.forEach { item ->
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("${item.food.name} x${item.quantity}")
                                Text("Rp${item.food.price * item.quantity}")
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Subtotal")
                            Text("Rp$subtotal")
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Ongkir")
                            Text("Rp$ongkir")
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total Pembayaran", fontWeight = FontWeight.Bold)
                            Text("Rp$total", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Pesanan Berhasil!") },
            text = { Text("Pesanan Anda sedang diproses.") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    viewModel.clearCart()
                    navigateHome()
                }) {
                    Text("Kembali ke Beranda")
                }
            }
        )
    }
}
