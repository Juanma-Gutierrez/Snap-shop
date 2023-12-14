package com.juanma_gutierrez.snapshop.data.models

import com.juanma_gutierrez.snapshop.data.repository.Product

/**
 * Clase que representa un elemento en el carrito de compras.
 */
data class CartItem(
    var product: Product,
    var quantity: Int,
)