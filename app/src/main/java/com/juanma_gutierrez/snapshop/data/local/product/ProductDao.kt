package com.juanma_gutierrez.snapshop.data.local.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz DAO (Objeto de Acceso a Datos) para la entidad de productos.
 */
@Dao
interface ProductDao {
    /**
     * Inserta una lista de entidades de productos en la base de datos.
     * @param listProductEntity La lista de entidades de productos a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createProduct(listProductEntity: List<ProductEntity>)

    /**
     * Inserta una entidad de producto en la base de datos.
     * @param productEntity La entidad de producto a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createProduct(productEntity: ProductEntity)

    /**
     * Obtiene todos los productos de la base de datos como un flujo.
     * @return Un flujo de la lista de entidades de productos.
     */
    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<ProductEntity>>
}

