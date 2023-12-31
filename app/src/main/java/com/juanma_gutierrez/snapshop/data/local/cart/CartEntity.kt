package com.juanma_gutierrez.snapshop.data.local.cart

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.services.CartItem

@Entity("cart")
data class CartEntity(
    @PrimaryKey val id: Int,
    val product: String,
    val productImage: String,
    val price: Double,
    val quantity: Int
)