package com.juanma_gutierrez.snapshop.data.local.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Clase de entidad que representa un elemento del carrito en la base de datos local.
 * @param id El ID único del elemento del carrito (generado automáticamente).
 * @param productId El ID del producto asociado al elemento del carrito.
 * @param productName El nombre del producto asociado al elemento del carrito.
 * @param productImage La URL de la imagen del producto asociado al elemento del carrito.
 * @param productPrice El precio del producto asociado al elemento del carrito.
 * @param quantity La cantidad del producto en el carrito.
 */
@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val productId: Int,
    val productName: String,
    val productImage: String,
    val productPrice: Double,
    val quantity: Int
)