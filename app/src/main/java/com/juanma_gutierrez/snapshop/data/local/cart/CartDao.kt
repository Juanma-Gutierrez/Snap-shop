package com.juanma_gutierrez.snapshop.data.local.cart

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow // Sirve para crear un observable

/**
 * Interfaz DAO (Objeto de Acceso a Datos) para la entidad de carrito.
 */
@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCart(cartEntity: CartEntity)

    @Query("SELECT * FROM cart")
    fun getAllProductsCart(): Flow<List<CartEntity>>
}
