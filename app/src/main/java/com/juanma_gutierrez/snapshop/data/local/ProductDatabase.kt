package com.juanma_gutierrez.snapshop.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Base de datos Room para almacenar la entidad de productos.
 * @property entities La lista de entidades que forman parte de la base de datos.
 * @property version La versión de la base de datos.
 */
@Database(
    entities = [ProductEntity::class],
    version = 1,
)
abstract class ProductsDatabase() : RoomDatabase() {
    /**
     * Devuelve una instancia del DAO de productos para interactuar con la base de datos.
     * @return El DAO de productos.
     */
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var _INSTANCE: ProductsDatabase? = null

        /**
         * Obtiene una instancia única de la base de datos o la crea si no existe.
         * @param context El contexto de la aplicación.
         * @return La instancia de la base de datos.
         */
        fun getInstance(context: Context): ProductsDatabase {
            return _INSTANCE ?: synchronized(this) {
                _INSTANCE ?: buildDatabase(context).also { db -> _INSTANCE = db }
            }
        }

        /**
         * Construye la base de datos Room.
         * @param context El contexto de la aplicación.
         * @return La instancia de la base de datos Room.
         */
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