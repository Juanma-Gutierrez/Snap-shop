package com.juanma_gutierrez.snapshop.data.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Clase de datos que representa un producto.
 *
 * @property id El identificador del producto.
 * @property title El título del producto.
 * @property price El precio del producto.
 * @property description La descripción del producto.
 * @property category La categoría del producto.
 * @property image La URL de la imagen del producto, puede ser nula.
 * @property rating La calificación del producto, puede ser nula.
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
