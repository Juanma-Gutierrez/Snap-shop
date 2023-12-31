package com.juanma_gutierrez.snapshop.data.repository

import com.juanma_gutierrez.snapshop.data.api.ProductApiRepository
import com.juanma_gutierrez.snapshop.data.api.asEntityModelList
import com.juanma_gutierrez.snapshop.data.local.product.ProductLocalRepository
import com.juanma_gutierrez.snapshop.data.local.product.asListProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Clase singleton que sirve como repositorio principal para manejar datos de productos,
 * combinando fuentes locales y de API.
 * @property localRepository Repositorio local para datos de productos.
 * @property apiRepository Repositorio de la API para datos de productos.
 */
@Singleton
data class ProductsRepository @Inject constructor(
    private val localRepository: ProductLocalRepository,
    private val apiRepository: ProductApiRepository
) {
    /**
     * Un flujo que representa todos los productos, obtenidos de la fuente local.
     */
    val allProducts: Flow<List<Product>>
        get() {
            return localRepository.allProducts.map { listProductEntity ->
                listProductEntity.asListProducts()
            }
        }

    /**
     * Actualiza la lista de productos mediante la obtención de datos desde la API
     * y la actualización de la base de datos local.
     */
    suspend fun refreshList() = withContext(Dispatchers.IO) {
        val productsApiModelList = apiRepository.getAll()
        localRepository.insert(productsApiModelList.asEntityModelList())
    }
}


