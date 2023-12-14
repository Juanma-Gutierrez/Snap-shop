package com.juanma_gutierrez.snapshop.data.api

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Clase de repositorio para acceder a datos de productos desde la API.
 * @property service El servicio que proporciona la comunicación con la API.
 */

@Singleton
class ProductApiRepository @Inject constructor(
    private val service: ProductService
) {
    /**
     * Obtiene la lista de productos desde la API y carga sus detalles.
     * @return La lista de [ProductApiModel] con detalles.
     */
    suspend fun getAll(): List<ProductApiModel> {
        val simpleList = service.api.getAll()
        return simpleList.map { p ->
            service.api.getDetail(p.id).asApiModel()
        }
    }

    suspend fun getUser(userId:String): User {
        val userData = service.api.getUserDetail(userId)
        return userData.asUser()
    }
}