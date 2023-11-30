package com.juanma_gutierrez.snapshop.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow // Sirve para crear un observable

@Dao
interface ProductDao {
    // Inserta productos en la BBDD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProduct(listProductEntity: List<ProductEntity>)

    // Sobrecarga para crear un único producto
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProduct(productEntity: ProductEntity)

    // Recupera todos los productos de la BBDD mediante un observable Flow
    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<ProductEntity>>
}
