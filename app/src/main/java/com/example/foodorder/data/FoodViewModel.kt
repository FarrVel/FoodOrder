package com.example.foodorder.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FoodViewModel : ViewModel() {
    private val _foods = MutableStateFlow(sampleFoods)
    val foods: StateFlow<List<Food>> = _foods.asStateFlow()

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    fun addToCart(food: Food, quantity: Int = 1) {
        val currentCart = _cartItems.value.toMutableList()
        val existingItem = currentCart.find { it.food.id == food.id }
        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            currentCart.add(CartItem(food, quantity))
        }
        _cartItems.value = currentCart
    }

    fun updateCartItemQuantity(foodId: String, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeFromCart(foodId)
            return
        }
        val currentCart = _cartItems.value.toMutableList()
        val itemIndex = currentCart.indexOfFirst { it.food.id == foodId }
        if (itemIndex != -1) {
            currentCart[itemIndex] = currentCart[itemIndex].copy(quantity = newQuantity)
            _cartItems.value = currentCart
        }
    }

    fun removeFromCart(foodId: String) {
        _cartItems.value = _cartItems.value.filter { it.food.id != foodId }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun getFoodById(id: String): Food? {
        return _foods.value.find { it.id == id }
    }
    
    fun toggleTheme(isDark: Boolean) {
        _isDarkMode.value = isDark
    }
}
