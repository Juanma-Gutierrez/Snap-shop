package com.juanma_gutierrez.snapshop.data.local.product

import androidx.annotation.WorkerThread
import com.juanma_gutierrez.snapshop.data.local.cart.CartDao
import com.juanma_gutierrez.snapshop.data.local.cart.CartEntity
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
    private val productDao: ProductDao,
    private val cartDao: CartDao
) {
    /**
     * Un flujo que representa todos los productos recuperados de la base de datos local.
     */
    val allProducts: Flow<List<ProductEntity>> = productDao.getAllProducts()

    /**
     * Un flujo que representa todos los productos del carrito de la base de datos local.
     */
    val allProductsCart: Flow<List<CartEntity>> = cartDao.getAllProductsCart()

    /**
     * Inserta una lista de entidades de productos en la base de datos local.
     * @param listProductEntity La lista de entidades de productos que se insertarán.
     */
    @WorkerThread
    suspend fun insertProduct(listProductEntity: List<ProductEntity>) =
        productDao.createProduct(listProductEntity)


    suspend fun insertProductCart(cartEntity: CartEntity) {
        cartDao.insertProductCart(cartEntity)
    }
}

