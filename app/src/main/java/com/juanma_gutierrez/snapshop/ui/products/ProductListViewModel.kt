package com.juanma_gutierrez.snapshop.ui.products

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
class ProductListViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {
    private val _productsList: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val productsList: StateFlow<List<Product>>
        get() = _productsList.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                repository.refreshList()
                Log.d("testing", "ProductList: ${_productsList.value.size}")
            } catch (e: IOException) {
                Log.e("ERROR", e.toString())
            }
        }
        viewModelScope.launch {
            repository.allProducts.collect {
                Log.d("testing", "ProductListViewModel: ${it.size}")
                _productsList.value = it
            }
        }
    }
}