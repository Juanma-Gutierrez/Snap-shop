package com.juanma_gutierrez.snapshop.ui.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {
    /**
     * Flujo mutable que representa la lista actual de productos en el carrito.
     */
    private val _productsInCartList: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val productsList: StateFlow<List<Product>>
        get() = _productsInCartList.asStateFlow()

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
                _productsInCartList.value = it
            }
        }
    }
}

