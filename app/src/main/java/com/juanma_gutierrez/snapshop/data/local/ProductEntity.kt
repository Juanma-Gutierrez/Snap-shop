package com.juanma_gutierrez.snapshop.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juanma_gutierrez.snapshop.data.repository.Product

// Define cómo es la tabla products de la BBDD
@Entity("product")
data class ProductEntity(
    @PrimaryKey val id: Long?,
    val name: String?,
    val price: Double?,
    val description: String?,
    val category: String?,
    val image: String?,
    val rate: Double?,
    val count: Long?
)

// Crea la función asListProducts() para devolver un List<Product> mapeado con name e image
fun List<ProductEntity>.asListProducts(): List<Product> {
    //  return this.map {
    //     Product(it.name, it.image)
    // }
    return emptyList()
}

