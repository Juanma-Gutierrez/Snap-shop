package com.juanma_gutierrez.snapshop.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

interface ProductApi {
    // ProductListResponse es la respuesta de la API
    @GET("products")
    suspend fun getAll(): List<ProductDetailResponse>


    // Devuelve uel detalle de un producto
    @GET("products/{id}")
    suspend fun getDetail(@Path("id") id: Long): ProductDetailResponse
}

// Con Singleton se indica que es un provider tipo singleton
@Singleton
class ProductService @Inject constructor() {
    // Instancia la clase retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Genera la api con el método create
    val api: ProductApi = retrofit.create(ProductApi::class.java)
}
