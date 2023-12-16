package com.juanma_gutierrez.snapshop.data.repository

import com.juanma_gutierrez.snapshop.data.api.ApiRepository
import com.juanma_gutierrez.snapshop.data.local.cart.CartDao
import com.juanma_gutierrez.snapshop.data.local.cart.CartEntity
import com.juanma_gutierrez.snapshop.data.models.asEntityModelList
import com.juanma_gutierrez.snapshop.data.local.product.ProductLocalRepository
import com.juanma_gutierrez.snapshop.data.local.product.asListProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Clase singleton que sirve como repositorio principal para manejar datos de productos,
 * combinando fuentes locales y de API.
 * @property localRepository Repositorio local para datos de productos.
 * @property apiRepository Repositorio de la API para datos de productos.
 */
@Singleton
data class DatabaseRepository @Inject constructor(
    private val localRepository: ProductLocalRepository,
    private val apiRepository: ApiRepository,
    private val cartDao: CartDao
) {
    /**
     * Un flujo que representa todos los productos, obtenidos de la fuente local.
     */
    val allProducts: Flow<List<Product>>
        get() {
            return localRepository.allProducts.map { listProductEntity ->
                listProductEntity.asListProducts()
            }
        }

    /**
     * Actualiza la lista de productos mediante la obtención de datos desde la API
     * y la actualización de la base de datos local.
     */
    suspend fun refreshList() = withContext(Dispatchers.IO) {
        val productsApiModelList = apiRepository.getAll()
        localRepository.insertProduct(productsApiModelList.asEntityModelList())
    }

    /**
     * Inserta un elemento en el carrito de compras.
     *
     * @param cartItem El elemento del carrito a insertar.
     */
    suspend fun insertProductCart(cartItem: CartEntity) {
        val existingCartItem = cartDao.getCartItem(cartItem.productId)
        if (existingCartItem != null) {
            // El producto ya existe, actualiza la cantidad
            val updatedQuantity = existingCartItem.quantity + 1
            val updatedCartItem = existingCartItem.copy(quantity = updatedQuantity)
            cartDao.updateCartItem(updatedCartItem)
        } else {
            // El producto no existe, realiza la inserción
            cartDao.insertProductCart(cartItem)
        }
    }

    /**
     * Elimina un elemento del carrito de compras.
     *
     * @param cartItem El elemento del carrito a eliminar.
     * @return `true` si la operación fue exitosa, `false` en caso contrario.
     */
    suspend fun deleteProductCart(cartItem: CartEntity): Boolean {
        val existingCartItem = cartDao.getCartItem(cartItem.productId)
        if (existingCartItem != null) {
            // El producto ya existe, actualiza la cantidad
            if (existingCartItem.quantity > 1) {
                val updatedQuantity = existingCartItem.quantity - 1
                val updatedCartItem = existingCartItem.copy(quantity = updatedQuantity)
                cartDao.updateCartItem(updatedCartItem)
            } else {
                // El producto está a 0, realiza su borrado
                cartDao.deleteCartItem(cartItem.productId)
            }
        }
        return true
    }

    /**
     * Un flujo que representa todos los elementos del carrito de compras.
     *
     * @return Un flujo de la lista de elementos del carrito.
     */
    val allProductsCart: Flow<List<CartEntity>>
        get() {
            return localRepository.allProductsCart.map { item ->
                item
            }
        }
}
