package com.juanma_gutierrez.snapshop.data.local.cart

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz DAO (Objeto de Acceso a Datos) para la entidad de carrito.
 */
@Dao
interface CartDao {
    /**
     * Inserta un elemento de carrito en la base de datos.
     * @param cartEntity El elemento del carrito que se va a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCart(cartEntity: CartEntity)

    /**
     * Obtiene todos los elementos del carrito almacenados en la base de datos como un flujo observable.
     * @return Un [Flow] que emite una lista de [CartEntity] cada vez que cambian los datos del carrito.
     */
    @Query("SELECT * FROM cart")
    fun getAllProductsCart(): Flow<List<CartEntity>>

    /**
     * Obtiene un elemento del carrito basado en el ID del producto.
     * @param productId El ID del producto del elemento del carrito que se va a obtener.
     * @return Un objeto [CartEntity] que representa el elemento del carrito encontrado o nulo si no existe.
     */
    @Query("SELECT * FROM cart WHERE productId = :productId")
    suspend fun getCartItem(productId: Int): CartEntity?

    /**
     * Actualiza un elemento del carrito en la base de datos.
     * @param cartItem El elemento del carrito que se va a actualizar.
     */
    @Update
    suspend fun updateCartItem(cartItem: CartEntity)

    /**
     * Elimina un elemento del carrito de la base de datos basado en el ID del producto.
     * @param productId El ID del producto del elemento del carrito que se va a eliminar.
     */
    @Query("DELETE FROM cart WHERE productId = :productId")
    suspend fun deleteCartItem(productId: Int)
}
