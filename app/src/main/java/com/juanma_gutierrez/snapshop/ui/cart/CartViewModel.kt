// CartService.kt

package com.juanma_gutierrez.snapshop.ui.cart

import android.util.Log
import com.juanma_gutierrez.snapshop.data.local.cart.CartEntity
import com.juanma_gutierrez.snapshop.data.repository.Cart
import com.juanma_gutierrez.snapshop.data.repository.DatabaseRepository
import com.juanma_gutierrez.snapshop.data.repository.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ViewModel para gestionar operaciones relacionadas con el carrito de compras.
 */
@Singleton
class CartViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    /**
     * Método para agregar un producto al carrito.
     * @param product El producto a agregar al carrito.
     */
    suspend fun addToCart(product: Product) = withContext(Dispatchers.IO) {
        val cartItem = CartEntity(
            productId = product.id,
            productName = product.title,
            productImage = product.image.orEmpty(),
            productPrice = product.price,
            quantity = 1
        )
        databaseRepository.insertProductCart(cartItem)
    }

    /**
     * Flujo que representa todos los productos en el carrito.
     */
    val allProductsCart: Flow<List<CartEntity>>
        get() {
            return databaseRepository.allProductsCart
        }

    /**
     * Método para eliminar un producto del carrito.
     * @param cartItem El elemento del carrito a eliminar.
     * @return `true` si la operación es exitosa, `false` en caso contrario.
     */
    suspend fun deleteFromCart(cartItem: Cart): Boolean = withContext(Dispatchers.IO) {
        try {
            val itemToDelete = CartEntity(
                productId = cartItem.productId,
                productName = cartItem.productName,
                productImage = cartItem.productImage.orEmpty(),
                productPrice = cartItem.productPrice,
                quantity = cartItem.quantity
            )
            databaseRepository.deleteProductCart(itemToDelete)
            return@withContext true
        } catch (e: Exception) {
            Log.e("Error", "Error al eliminar item: $e")
            return@withContext false
        }
    }

    /**
     * Método para obtener la cantidad de elementos en el carrito.
     * @return La cantidad de elementos en el carrito.
     */
    suspend fun getSize(): Int {
        return databaseRepository.allProductsCart.count()
    }
}
