package com.juanma_gutierrez.snapshop.data.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductApiRepository @Inject constructor(
    private val service: ProductService
) {
    // Mapea cada item recibido para cargar el detalle del mismo.
    suspend fun getAll(): List<ProductApiModel> {
        val simpleList = service.api.getAll()
        return simpleList.map { p ->
            service.api.getDetail(p.title).asApiModel()
        }
    }
}