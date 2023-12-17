package com.juanma_gutierrez.snapshop.ui.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation.findNavController
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.adapter.ProductAdapter
import com.juanma_gutierrez.snapshop.ui.cart.CartViewModel
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.databinding.FragmentProductListBinding
import com.juanma_gutierrez.snapshop.ui.MainActivity
import com.juanma_gutierrez.snapshop.ui.filter.FilterService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Fragment que muestra una lista de productos en un recycler view.
 */
@AndroidEntryPoint
class ProductListFragment @Inject constructor() : Fragment() {
    @Inject
    lateinit var cartSvc: CartViewModel
    lateinit var filterSvc: FilterService
    private val viewModel: ProductListViewModel by viewModels()
    private lateinit var binding: FragmentProductListBinding

    /**
     * Crea y retorna la vista asociada al fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Carga la lista de productos en el recycler view.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filterSvc = FilterService
        binding.topTbListToolbar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.tb_topTopbar_filter_button -> {
                    findNavController(view).navigate(R.id.action_productListFragment_to_filterFragment)
                    true
                }
                else -> false
            }
        }
        binding.llIsLoading.visibility = VISIBLE
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productsList.collect { originalList ->

                    val filteredList = originalList.filter { product ->
                        // Aplica el filtro al listado antes de pasarlo al adaptador
                        productInFilter(product)
                    }
                    binding.llIsLoading.visibility = GONE
                    val adapter = ProductAdapter(filteredList, ::onDetail, cartSvc)
                    binding.rvFragmentProductList.adapter = adapter
                }
            }
        }
    }

    private fun productInFilter(product: Product): Boolean {
        var valid = true
        val filterSvc = FilterService
        // Filtra por precio
        if (product.price > filterSvc.maxPrice) {
            valid = false
        }
        // Filtra por categoría
        if (!(product.category.equals(filterSvc.category) || filterSvc.category.equals("none"))) {
            valid = false
        }
        // Filtra por puntuación
        if (product.rating!!.rate!! < filterSvc.rating) {
            valid = false
        }
        return valid
    }
}

/**
 * Navega al detalle del producto al hacer clic en él.
 */
fun onDetail(product: Product, view: View) {
    val bundle = Bundle()
    bundle.putParcelable("product", product)
    val navController = findNavController(view)
    navController.navigate(R.id.action_productListFragment_to_productDetailFragment, bundle)
}

