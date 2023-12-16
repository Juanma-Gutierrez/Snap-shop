package com.juanma_gutierrez.snapshop.data.local.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val productId: Int,
    val productName: String,
    val productImage: String,
    val productPrice: Double,
    val quantity: Int
) {
    /**
     * Convierte la entidad [CartEntity] a un objeto [Cart].
     * @return El objeto [Cart].
     */
    /*
      fun asCartItem(): Cart {
          return Cart(
              productId,
              quantity
          )
      }

     */
}


/**
 * Extensión de la lista de [CartEntity] que convierte la lista a una lista de [Cart].
 * @return La lista de [Cart].
 */
/* fun List<CartEntity>.asListProductsCart(): List<Cart> {
    return this.map {
        it.asCartItem()
    }
    }
 */

