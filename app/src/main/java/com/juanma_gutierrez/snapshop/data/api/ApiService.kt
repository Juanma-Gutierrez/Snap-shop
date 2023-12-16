package com.juanma_gutierrez.snapshop.data.api

import com.juanma_gutierrez.snapshop.data.models.ProductDetailResponse
import com.juanma_gutierrez.snapshop.data.models.UserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Interfaz para definir los puntos finales de la API relacionados con productos.
 */
interface ProductApi {
    @GET("products")
    suspend fun getAll(): List<ProductDetailResponse>

    @GET("products/{id}")
    suspend fun getDetail(@Path("id") id: Int): ProductDetailResponse

    @GET("products/category/{category}")
    suspend fun getProductByCategory(@Path("category") category: String): ProductDetailResponse

    @GET("users/{userId}")
    suspend fun getUserDetail(@Path("userId") userId: String): UserResponse
}

/**
 * Clase singleton que proporciona acceso a los servicios de la API relacionados con productos.
 */
@Singleton
class ProductService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Instancia de la API de productos creada a través de Retrofit.
     */
    val api: ProductApi = retrofit.create(ProductApi::class.java)
}
