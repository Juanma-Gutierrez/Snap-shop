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
import com.juanma_gutierrez.snapshop.data.repository.CartService
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.databinding.FragmentProductDetailBinding
// import com.juanma_gutierrez.snapshop.services.CartService
import com.juanma_gutierrez.snapshop.services.Services
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Fragment que muestra los detalles de un producto.
 */
@AndroidEntryPoint
class ProductDetailFragment @Inject constructor() : Fragment() {
    @Inject
    lateinit var cartSvc: CartService
    private lateinit var binding: FragmentProductDetailBinding
    private val args: ProductDetailFragmentArgs by navArgs()
    val svc = Services()

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
        super.onViewCreated(view, savedInstanceState)

        val selectedProduct: Product = args.product
        Glide.with(view)
            .load(selectedProduct.image)
            .into(binding.ivDetailImage)
        binding.rbRatingStars.rating = selectedProduct.rating?.rate?.toFloat()!!
        binding.tvDetailCount.text =
            resources.getString(
                R.string.fr_productDetail_rating_detail,
                selectedProduct.rating?.count
            )
        binding.tvDetailName.text = selectedProduct.title
        binding.chDetailCategory.text = selectedProduct.category
        binding.tvDetailPrice.text = svc.formatPrice(selectedProduct.price)
        binding.tvDetailDescription.text = selectedProduct.description
        binding.btDetailAddToCart.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    cartSvc.addToCart(selectedProduct)
                    val message = getString(R.string.fr_productDetail_snackbar_message)
                    svc.showSnackBar(message, view)
                } catch (e: Exception) {
                    Log.e("error", "Error adding item to cart: $e")
                }
            }
        }
    }
}