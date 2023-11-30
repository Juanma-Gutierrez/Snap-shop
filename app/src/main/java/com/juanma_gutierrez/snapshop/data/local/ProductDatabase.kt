package com.juanma_gutierrez.snapshop.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ProductEntity::class],
    version = 1,
    // autoMigrations = [AutoMigration(from = 1, to = 2)]
)
abstract class ProductsDatabase() : RoomDatabase() {
    // Crea una única instancia de la BBDD utilizando singleton.
    // Trabaja en hilos asegurándose de que dos hilos no acceden al mismo recurso a la vez.
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var _INSTANCE: ProductsDatabase? = null

        // Devuelve una instancia de la base de datos
        fun getInstance(context: Context): ProductsDatabase {
            return _INSTANCE ?: synchronized(this) {
                _INSTANCE ?: buildDatabase(context).also { db -> _INSTANCE = db }
            }
        }

        private fun buildDatabase(context: Context): ProductsDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ProductsDatabase::class.java,
                "products_db"
            )
                .build()
        }
    }
}