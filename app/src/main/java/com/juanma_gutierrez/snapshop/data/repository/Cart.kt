package com.juanma_gutierrez.snapshop.data.repository

import com.juanma_gutierrez.snapshop.data.local.cart.CartEntity

/**
 * Clase que representa un elemento en el carrito de compras.
 */
data class Cart(
    val productId: Int,
    val quantity: Int
) {
    fun asEntity(): CartEntity {
        return CartEntity(
            productId,
            productId,
            1
        )
    }
}