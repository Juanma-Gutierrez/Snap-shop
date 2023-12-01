package com.juanma_gutierrez.snapshop.ui.products

import android.os.Bundle
import android.util.Log
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
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        binding.llIsLoading.visibility = VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productsList.collect {
                    Log.d("testing", "OnViewCreated en ProductListFragment it: ${it.size}")
                    binding.llIsLoading.visibility = View.GONE
                    val adapter = ProductAdapter(it, ::onDetail)
                    binding.rvFragmentProductList.adapter = adapter
                }
            }
        }
    }

    fun onDetail(product: Product, view: View) {
        val bundle = Bundle()
        bundle.putParcelable("product", product)
        val navController = findNavController(view)
        navController.navigate(R.id.action_productListFragment_to_productDetailFragment, bundle)
    }
}