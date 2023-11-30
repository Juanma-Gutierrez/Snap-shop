package com.juanma_gutierrez.snapshop.data.repository

import com.juanma_gutierrez.snapshop.data.api.ProductApiRepository
import com.juanma_gutierrez.snapshop.data.api.asEntityModelList
import com.juanma_gutierrez.snapshop.data.local.ProductLocalRepository
import com.juanma_gutierrez.snapshop.data.local.asListProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


// Repository es el que va a usar el repo bd y otro el repo de red, unificando todas las fuentes de datos
// en un solo modelo, es la capa de salida
@Singleton
data class ProductsRepository @Inject constructor(
    private val localRepository: ProductLocalRepository,
    private val apiRepository: ProductApiRepository
) {
    // Aquí ya la salida es tipo lista de Product
    val allProducts: Flow<List<Product>>
        get() {
            // Primero le pide a la bbdd que devuelva la lista de productos
            return localRepository.allProducts.map { listProductEntity ->
                listProductEntity.asListProducts()
            }
        }

    // El código que se va a ejecutar deentro es suspendible en una corrutina
    // Como es una operación de red, está obligado a hacerlo en el IO por el Dispatchers
    // Las corrutinas no están asociadas a un hilo, es recomendable usar el contexto Dispatchers.IO
    suspend fun refreshList() = withContext(Dispatchers.IO) {
        val productsApiModelList = apiRepository.getAll()
        localRepository.insert(productsApiModelList.asEntityModelList())
    }
}


