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

data class UserResponse(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val name: UserName,
    val address: UserAddress,
    val phone: String,
) {
    /**
     * Convierte la respuesta del usuario a un [User].
     * @return [User].
     */
    fun asUser(): User {
        return User(
            id,
            email,
            username,
            password,
            name.firstname,
            name.lastname,
            address.city,
            address.street,
            address.number,
            address.zipcode,
            phone,
        )
    }
}

data class UserName(
    val firstname: String,
    val lastname: String,
)

data class UserAddress(
    val city: String,
    val street: String,
    val number: Long,
    val zipcode: String,
)

data class User(
    val id: Int,
    val email: String,
    val userName: String,
    val password: String,
    val firstName: String,
    val surname: String,
    val city: String,
    val street: String,
    val number: Long,
    val zipcode: String,
    val phone: String,
)
