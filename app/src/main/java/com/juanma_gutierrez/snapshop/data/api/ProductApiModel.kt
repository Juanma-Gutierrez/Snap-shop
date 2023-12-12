package com.juanma_gutierrez.snapshop.data.api

import com.juanma_gutierrez.snapshop.data.local.product.ProductEntity
import com.juanma_gutierrez.snapshop.data.repository.Rating

/**
 * Modelo de datos para representar un producto proveniente de la API.
 */
data class ProductApiModel(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rate: Double,
    val count: Int,
)

/**
 * Extensión de la lista de [ProductApiModel] que convierte la lista a una lista de [ProductEntity].
 */
fun List<ProductApiModel>.asEntityModelList(): List<ProductEntity> {
    return this.map {
        ProductEntity(
            it.id,
            it.name,
            it.price,
            it.description,
            it.category,
            it.image,
            it.rate,
            it.count
        )
    }
}

/**
 * Respuesta de lista de productos obtenidos de la API.
 */
data class ProductListResponse(
    val results: List<ProductDetailResponse>
)

/**
 * Respuesta detallada de un producto obtenido de la API.
 */
data class ProductDetailResponse(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating,
) {
    /**
     * Convierte la respuesta detallada del producto a un [ProductApiModel].
     * @return El [ProductApiModel].
     */
    fun asApiModel(): ProductApiModel {
        return ProductApiModel(
            id,
            title,
            price,
            description,
            category,
            image,
            rating.rate,
            rating.count,
        )
    }
}



