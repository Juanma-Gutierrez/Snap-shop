package com.juanma_gutierrez.snapshop.data.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Clase de datos que representa un producto.
 */
@Parcelize
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String?,
    val rating: Rating?,
) : Parcelable

/**
 * Clase de datos que representa la calificación de un producto.
 * @property rate La calificación del producto.
 * @property count La cantidad de calificaciones del producto.
 */
@Parcelize
data class Rating(
    val rate: Double,
    val count: Int,
) : Parcelable
