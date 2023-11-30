package com.juanma_gutierrez.snapshop.di


import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Se le dice a Hilt que es una aplicación de Android
@HiltAndroidApp
class ProductAplication : Application() {
}