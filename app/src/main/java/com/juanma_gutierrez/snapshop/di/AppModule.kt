package com.juanma_gutierrez.snapshop.di

import android.content.Context
import com.juanma_gutierrez.snapshop.data.local.cart.CartDao
import com.juanma_gutierrez.snapshop.data.local.product.ProductDao
import com.juanma_gutierrez.snapshop.data.local.database.ProductsDatabase
import com.juanma_gutierrez.snapshop.ui.cart.CartViewModel
import com.juanma_gutierrez.snapshop.data.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo Dagger que proporciona las instancias de la base de datos y el DAO de productos.
 *
 * @property context El contexto de la aplicación proporcionado por Dagger Hilt.
 */
@Module
@InstallIn(SingletonComponent::class) // Se instala en toda la aplicación
object AppModule {
    /**
     * Provee la instancia única de la base de datos de productos.
     *
     * @param context El contexto de la aplicación proporcionado por Dagger Hilt.
     * @return La instancia de la base de datos de productos.
     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ProductsDatabase {
        return ProductsDatabase.getInstance(context)
    }

    /**
     * Provee el DAO (Objeto de Acceso a Datos) de productos.
     *
     * @param database La base de datos de productos.
     * @return El DAO de productos.
     */
    @Singleton
    @Provides
    fun provideProductDao(database: ProductsDatabase): ProductDao {
        return database.productDao()
    }

    /**
     * Provee el DAO (Objeto de Acceso a Datos) de carrito.
     *
     * @param database La base de datos de productos.
     * @return El DAO de carrito.
     */
    @Singleton
    @Provides
    fun provideCartDao(database: ProductsDatabase): CartDao {
        return database.cartDao()
    }

    /**
     * Provee el servicio del carrito.
     *
     * @param databaseRepository El repositorio de la base de datos.
     * @return El ViewModel del carrito.
     */
    @Singleton
    @Provides
    fun provideCartService(
        databaseRepository: DatabaseRepository
    ): CartViewModel {
        return CartViewModel(databaseRepository)
    }
}