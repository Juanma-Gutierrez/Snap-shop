package com.juanma_gutierrez.snapshop.data.api

import com.juanma_gutierrez.snapshop.data.local.ProductEntity
import com.juanma_gutierrez.snapshop.data.repository.Rating

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

// Realiza el mapeo de List<ProductApiModel> a List<ProductEntity>
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

// Resultado de la respuesta de la API, una lista de items productos
data class ProductListResponse(
    val results: List<ProductDetailResponse>
)

data class ProductDetailResponse(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating,
) {
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



