package com.juanma_gutierrez.snapshop.ui.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

/**
 * ViewModel para la lista de productos en la interfaz de usuario.
 * Hilt simplifica la inyección de dependiencias utilizando Dagger.
 * @property repository El repositorio de productos que maneja la lógica de obtención de datos.
 */
@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: DatabaseRepository
) : ViewModel() {
    /**
     * Flujo mutable que representa la lista actual de productos.
     */
    private val _productsList: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val productsList: StateFlow<List<Product>>
        get() = _productsList.asStateFlow()

    /**
     * Inicializa el ViewModel y realiza la carga inicial de datos.
     */
    init {
        viewModelScope.launch {
            try {
                // Refresca la lista de productos al iniciar
                repository.refreshList()
            } catch (e: IOException) {
                Log.e("ERROR", e.toString())
            }
        }
        // Observa cambios en la lista de productos y actualiza el flujo
        viewModelScope.launch {
            repository.allProducts.collect {
                _productsList.value = it
            }
        }
    }
}