package com.juanma_gutierrez.snapshop.ui.products

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.ui.cart.CartViewModel
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.databinding.FragmentProductDetailBinding
import com.juanma_gutierrez.snapshop.services.Services
import com.juanma_gutierrez.snapshop.ui.MainActivity
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
    lateinit var cartSvc: CartViewModel
    private lateinit var binding: FragmentProductDetailBinding
    private val args: ProductDetailFragmentArgs by navArgs()
    val svc = Services()

    /**
     * Método invocado cuando la actividad asociada al fragmento ha sido creada.
     * Se utiliza para ocultar la barra superior (toolbar) de la actividad principal, si está presente.
     *
     * @param savedInstanceState El estado de la instancia guardada de la actividad.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mainActivity = activity as? MainActivity
        // mainActivity?.showTopListToolBar("detail")
    }

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
        binding.tbFrDetailTopBar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.tb_topbar_share_button -> {
                    onShare(selectedProduct)
                    true // Devuelve true para indicar que el evento ha sido manejado
                }
                else -> false
            }
        }
    }

    /**
     * Comparte la información del producto seleccionado.
     *
     * @param selectedProduct El producto seleccionado que se compartirá.
     */
    private fun onShare(selectedProduct: Product) {
        val shareText = getString(
            R.string.share_text,
            selectedProduct.title,
            selectedProduct.category,
            selectedProduct.price.toString()
        )
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }
}