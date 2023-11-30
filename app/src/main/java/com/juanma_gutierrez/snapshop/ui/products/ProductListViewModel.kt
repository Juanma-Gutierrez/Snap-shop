package com.juanma_gutierrez.snapshop.ui.products

import androidx.lifecycle.ViewModel
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {
    private val _productsList: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val productsList: StateFlow<List<Product>>
        get() = _productsList.asStateFlow()
}