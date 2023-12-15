package com.juanma_gutierrez.snapshop.ui.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanma_gutierrez.snapshop.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: DatabaseRepository
) : ViewModel() {
    /**
     * Flujo mutable que representa la lista actual de productos en el carrito.
     */
    /*
    private val _productsInCartList: MutableStateFlow<List<Cart>> = MutableStateFlow(listOf())
    val productsList: StateFlow<List<Cart>>
        get() = _productsInCartList.asStateFlow()
*/
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
        /*
        viewModelScope.launch {
            repository.allProductsCart.collect {
                _productsInCartList.value = it
            }
        }

         */
    }
}

