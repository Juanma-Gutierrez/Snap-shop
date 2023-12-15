// CartService.kt

package com.juanma_gutierrez.snapshop.data.repository

import android.util.Log
import com.juanma_gutierrez.snapshop.data.local.cart.CartEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Servicio para gestionar operaciones relacionadas con el carrito de compras.
 */
@Singleton
class CartService @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    suspend fun addToCart(product: Product) = withContext(Dispatchers.IO) {
        val cartItem = CartEntity(productId = product.id, quantity = 1)
        Log.d("testing", "Insertando item ${cartItem.id}, cantidad ${cartItem.quantity}")
        databaseRepository.insertProductCart(cartItem)
    }

    /*
    val allProductsCart: Flow<List<CartEntity>>
        get() {
            return databaseRepository.allProducts
        }
*/

    // Puedes agregar más funciones según sea necesario, como removeFromCart, clearCart, etc.
}
