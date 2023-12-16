package com.juanma_gutierrez.snapshop.data.repository

import com.juanma_gutierrez.snapshop.data.local.cart.CartEntity

/**
 * Clase de datos que representa un elemento en el carrito de compras.
 *
 * @property id El identificador único del elemento del carrito.
 * @property productId El identificador del producto asociado al elemento del carrito.
 * @property productName El nombre del producto en el carrito.
 * @property productImage La URL de la imagen del producto en el carrito.
 * @property productPrice El precio del producto en el carrito.
 * @property quantity La cantidad del producto en el carrito.
 */
data class Cart(
    val id: Int?,
    val productId: Int,
    val productName: String,
    val productImage: String?,
    val productPrice: Double,
    val quantity: Int
) {
    /**
     * Convierte la instancia de [Cart] a una instancia de [CartEntity].
     *
     * @return Una instancia de [CartEntity].
     */
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

    /**
     * Convierte la instancia de [Cart] a una instancia de [Product].
     *
     * @return Una instancia de [Product].
     */
    fun asProduct(): Product {
        return Product(
            id = productId,
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