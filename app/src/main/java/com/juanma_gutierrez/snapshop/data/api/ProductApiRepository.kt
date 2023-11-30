package com.juanma_gutierrez.snapshop.data.api

import android.util.Log
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
            Log.d("testing","getAll: ${p.title}")
            service.api.getDetail(p.id).asApiModel()
        }
    }
}