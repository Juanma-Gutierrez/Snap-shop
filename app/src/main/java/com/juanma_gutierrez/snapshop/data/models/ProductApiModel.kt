package com.juanma_gutierrez.snapshop.data.models

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

/**
 * Clase de datos que representa la respuesta de un usuario desde la API.
 *
 * @property id El identificador único del usuario.
 * @property email La dirección de correo electrónico del usuario.
 * @property username El nombre de usuario del usuario.
 * @property password La contraseña del usuario.
 * @property name El objeto [UserName] que contiene el nombre y apellido del usuario.
 * @property address El objeto [UserAddress] que contiene la dirección del usuario.
 * @property phone El número de teléfono del usuario.
 */
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

/**
 * Clase de datos que representa el nombre de un usuario.
 *
 * @property firstname El primer nombre del usuario.
 * @property lastname El apellido del usuario.
 */
data class UserName(
    val firstname: String,
    val lastname: String,
)

/**
 * Clase de datos que representa la dirección de un usuario.
 *
 * @property city La ciudad de residencia del usuario.
 * @property street La calle de residencia del usuario.
 * @property number El número de residencia del usuario.
 * @property zipcode El código postal de la residencia del usuario.
 */
data class UserAddress(
    val city: String,
    val street: String,
    val number: Long,
    val zipcode: String,
)

/**
 * Clase de datos que representa un usuario.
 *
 * @property id El identificador único del usuario.
 * @property email La dirección de correo electrónico del usuario.
 * @property userName El nombre de usuario del usuario.
 * @property password La contraseña del usuario.
 * @property firstName El primer nombre del usuario.
 * @property surname El apellido del usuario.
 * @property city La ciudad de residencia del usuario.
 * @property street La calle de residencia del usuario.
 * @property number El número de residencia del usuario.
 * @property zipcode El código postal de la residencia del usuario.
 * @property phone El número de teléfono del usuario.
 */
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
