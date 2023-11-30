package com.juanma_gutierrez.snapshop.di

import android.content.Context
import com.juanma_gutierrez.snapshop.data.local.ProductDao
import com.juanma_gutierrez.snapshop.data.local.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Especifica cómo crear la bbdd y cómo obtener la bbdd
@Module
@InstallIn(SingletonComponent::class) // Se instala en toda la aplicación
object DatabaseModule {
    //Instancia la base de datos y la provee
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ProductsDatabase {
        return ProductsDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideProductDao(database: ProductsDatabase): ProductDao {
        return database.productDao()
    }
}