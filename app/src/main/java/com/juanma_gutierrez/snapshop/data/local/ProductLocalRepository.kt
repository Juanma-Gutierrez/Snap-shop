package com.juanma_gutierrez.snapshop.data.local

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Clase singleton que sirve como repositorio local para manejar datos de productos desde una base
 * de datos local.
 * @property productDao El Objeto de Acceso a Datos (DAO) para entidades de productos.
 */
@Singleton
class ProductLocalRepository @Inject constructor(
    private val productDao: ProductDao
) {
    /**
     * Un flujo que representa todos los productos recuperados de la base de datos local.
     */
    val allProducts: Flow<List<ProductEntity>> = productDao.getAllProducts()

    /**
     * Inserta una lista de entidades de productos en la base de datos local.
     * @param listProductEntity La lista de entidades de productos que se insertarán.
     */
    @WorkerThread
    suspend fun insert(listProductEntity: List<ProductEntity>) =
        productDao.createProduct(listProductEntity)
}

