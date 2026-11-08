package com.example.foodorder.data

data class Food(
    val id: String,
    val name: String,
    val price: Int,
    val description: String,
    val imageUrl: String,
    val category: String,
    val rating: Double
)

val sampleFoods = listOf(
    Food(
        "1", "Pizza Margherita", 60000, 
        "Classic pizza dengan saus tomat segar, keju mozzarella, dan daun basil.", 
        "https://images.unsplash.com/photo-1604068549290-dea0e4a305ca?q=80&w=400&auto=format&fit=crop", 
        "Pizza", 4.8
    ),
    Food(
        "2", "Burger Premium", 55000, 
        "Burger premium dengan daging sapi pilihan, keju leleh, dan saus spesial.", 
        "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?q=80&w=400&auto=format&fit=crop", 
        "Burger", 4.5
    ),
    Food(
        "3", "Sushi Mix", 80000, 
        "Pilihan sushi segar berkualitas tinggi termasuk salmon, tuna, dan ebi.", 
        "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?q=80&w=400&auto=format&fit=crop", 
        "Sushi", 4.7
    ),
    Food(
        "5", "Coca Cola Zero", 15000, 
        "Minuman ringan tanpa kalori yang menyegarkan.", 
        "https://images.unsplash.com/photo-1622483767028-3f66f32aef97?q=80&w=400&auto=format&fit=crop", 
        "Minuman", 4.9
    )
)
