package com.juanma_gutierrez.snapshop.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.data.repository.Rating

// Define cómo es la tabla products de la BBDD
@Entity("product")
data class ProductEntity(
    @PrimaryKey val id: Long,
    val title: String?,
    val price: Double?,
    val description: String?,
    val category: String?,
    val image: String?,
    val rate: Double?,
    val count: Long?
) {
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


// Crea la función asListProducts() para devolver un List<Product> mapeado con name e image
fun List<ProductEntity>.asListProducts(): List<Product> {
    return this.map {
        it.asProduct()
    }
}

