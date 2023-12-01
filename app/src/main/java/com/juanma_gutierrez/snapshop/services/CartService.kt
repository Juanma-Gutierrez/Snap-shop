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
    var cartList: MutableList<CartItem> = mutableListOf()

    /**
     * Añade un producto al carrito con la cantidad especificada.
     * @param product El producto a añadir.
     * @param quantity La cantidad de unidades del producto.
     */
    fun addProduct(product: Product, quantity: Int) {
        cartList.add(CartItem(product, quantity))

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
    fun updateProduct(product: Product, quantity: Int){
        // todo encontrar el elemento que hay que modificar
    }
}