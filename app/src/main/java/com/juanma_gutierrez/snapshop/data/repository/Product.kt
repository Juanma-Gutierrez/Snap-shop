package com.juanma_gutierrez.snapshop.data.repository

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String?,
    val rating: Rating?,
)

data class Rating(
    val rate: Double,
    val count: Long,
)
