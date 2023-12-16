package com.juanma_gutierrez.snapshop.data.repository

import com.juanma_gutierrez.snapshop.data.local.cart.CartEntity

/**
 * Clase que representa un elemento en el carrito de compras.
 */
data class Cart(
    val id: Int?,
    val productId: Int,
    val productName: String,
    val productImage: String?,
    val productPrice: Double,
    val quantity: Int
) {
    fun asEntity(): CartEntity {
        return CartEntity(
            id = id,
            productId = productId,
            productName = productName,
            productImage = productImage.orEmpty(),
            productPrice = productPrice,
            quantity = quantity
        )
    }

    fun asProduct():Product{
        return Product(
            id=productId,
            title = productName,
            price = productPrice,
            description = "",
            category = "",
            image = productImage,
            Rating(
                rate = 0.0,
                count = 0
            )
        )
    }
}