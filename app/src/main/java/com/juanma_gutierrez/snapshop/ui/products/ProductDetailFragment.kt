package com.juanma_gutierrez.snapshop.ui.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.databinding.FragmentProductDetailBinding
import com.juanma_gutierrez.snapshop.services.Services

/**
 * Fragment que muestra los detalles de un producto.
 */
class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private val args: ProductDetailFragmentArgs by navArgs()

    /**
     * Crea y retorna la vista asociada al fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    /**
     * Carga la información del producto en la interfaz.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val svc = Services()
        super.onViewCreated(view, savedInstanceState)
        val selectedProduct: Product = args.product
        Log.d("testing", "onViewCreated: ${selectedProduct.title}")
        Glide.with(view)
            .load(selectedProduct.image)
            .into(binding.ivDetailImage)
        binding.rbRatingStars.rating = selectedProduct.rating?.rate?.toFloat()!!
        binding.tvDetailCount.text = resources.getString(R.string.rating_detail, selectedProduct.rating?.count)

        // binding.tvDetailCount.text = selectedProduct.rating?.count.toString() +"valoraciones"
        binding.tvDetailName.text = selectedProduct.title
        binding.chDetailCategory.text = selectedProduct.category
        binding.tvDetailPrice.text = svc.formatPrice(selectedProduct.price)
        binding.tvDetailDescription.text = selectedProduct.description
    }
}