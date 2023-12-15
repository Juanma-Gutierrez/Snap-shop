package com.juanma_gutierrez.snapshop.di


import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Clase de aplicación base para la aplicación de productos con Dagger Hilt.
 * Indica a Hilt que es una aplicación de Android.
 */
@HiltAndroidApp
class AppApplication : Application() {
}