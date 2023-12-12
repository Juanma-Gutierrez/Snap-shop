package com.juanma_gutierrez.snapshop.data.local.product

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.data.repository.Rating

/**
 * Entidad de Room para representar un producto en la base de datos local.
 */
@Entity("product")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String?,
    val price: Double?,
    val description: String?,
    val category: String?,
    val image: String?,
    val rate: Double?,
    val count: Int?
) {
    /**
     * Convierte la entidad [ProductEntity] a un objeto [Product].
     * @return El objeto [Product].
     */
    fun asProduct(): Product {
        return Product(
            id,
            title ?: "",
            price ?: 0.0,
            description ?: "",
            category ?: "",
            image,
            Rating(rate ?: 0.0, count ?: 0)
        )
    }
}

/**
 * Extensión de la lista de [ProductEntity] que convierte la lista a una lista de [Product].
 * @return La lista de [Product].
 */
fun List<ProductEntity>.asListProducts(): List<Product> {
    return this.map {
        it.asProduct()
    }
}

