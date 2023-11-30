package com.juanma_gutierrez.snapshop.data.local

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductLocalRepository @Inject constructor(private val productDao: ProductDao) {
    val allProducts: Flow<List<ProductEntity>> = productDao.getAllProducts()

    // Se hace la operación en un hilo de background
    @WorkerThread
    suspend fun insert(listProductEntity: List<ProductEntity>) =
        productDao.createProduct(listProductEntity)
}

