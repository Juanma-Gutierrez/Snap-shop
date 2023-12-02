package com.juanma_gutierrez.snapshop.services

import com.juanma_gutierrez.snapshop.data.repository.Product

/**
 * Clase que representa un elemento en el carrito de compras.
 */
data class CartItem(
    var product: Product,
    var quantity: Int,
)

/**
 * Servicio que gestiona el carrito de compras.
 */
class CartService {
    private val cartList: MutableList<CartItem> = mutableListOf()
    private var amount: Int = 0
    private var quantityItems: Int = 0

    companion object {
        @Volatile
        private var _INSTANCE: CartService? = null
    }

    fun getInstance(): CartService {
        return _INSTANCE ?: synchronized(this) {
            _INSTANCE ?: CartService().also { _INSTANCE = it }
        }
    }

    fun getSize(): Int {
        return cartList.size
    }

    fun getCartList(): List<CartItem> {
        return cartList.toList()
    }

    /**
     * Añade un producto al carrito con la cantidad especificada.
     * @param product El producto a añadir.
     * @param quantity La cantidad de unidades del producto.
     */
    fun addProduct(product: Product) {
        val existingItem = cartList.find { it.product == product }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartList.add(CartItem(product, 1))
        }
        quantityItems++
        // calculateAmount()
    }

    private fun calculateAmount() {
        TODO("Not yet implemented")
    }

    /**
     * Elimina un producto del carrito con la cantidad especificada.
     * @param product El producto a eliminar.
     * @param quantity La cantidad de unidades del producto.
     */
    fun removeProduct(product: Product, quantity: Int) {
        cartList.remove(CartItem(product, quantity))
    }

    /**
     * Actualiza la cantidad de un producto en el carrito.
     * @param product El producto a actualizar.
     * @param quantity La nueva cantidad de unidades del producto.
     */
    fun updateProduct(product: Product, quantity: Int) {
        // todo encontrar el elemento que hay que modificar
    }
}