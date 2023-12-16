package com.juanma_gutierrez.snapshop.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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
    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductListViewModel by viewModels()

    /**
     * Método invocado cuando la actividad asociada al fragmento ha sido creada.
     * Se utiliza para mostrar la barra superior (toolbar) de la actividad principal, si está presente.
     *
     * @param savedInstanceState El estado de la instancia guardada de la actividad.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mainActivity = activity as? MainActivity
        mainActivity?.showTopListToolBar("list")
    }

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
        viewLifecycleOwner.lifecycleScope.launch {
            binding.llIsLoading.visibility = VISIBLE
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productsList.collect {
                    val adapter = ProductAdapter(it, ::onDetail, cartSvc)
                    binding.rvFragmentProductList.adapter = adapter
                    binding.llIsLoading.visibility = View.GONE
                }
            }
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
}