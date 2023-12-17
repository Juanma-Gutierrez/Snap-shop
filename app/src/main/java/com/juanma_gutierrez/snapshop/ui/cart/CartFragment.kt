package com.juanma_gutierrez.snapshop.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanma_gutierrez.snapshop.adapter.CartItemAdapter
import com.juanma_gutierrez.snapshop.data.repository.Cart
import com.juanma_gutierrez.snapshop.databinding.FragmentCartBinding
import com.juanma_gutierrez.snapshop.services.Services
import com.juanma_gutierrez.snapshop.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Fragmento que muestra los elementos del carrito de compras.
 */
@AndroidEntryPoint
class CartFragment : Fragment() {
    @Inject
    lateinit var cartSvc: CartViewModel
    private lateinit var binding: FragmentCartBinding

    /**
     * Método llamado al crear la vista del fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Método llamado cuando la vista ha sido creada.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartIsEmpty()
        lifecycleScope.launchWhenStarted {
            try {
                cartSvc.allProductsCart.collect { cartItems ->
                    val cartList: MutableList<Cart> = mutableListOf()
                    var totalAmount = 0.0
                    // Recorro toda el carrito y lo añado a cartList
                    if (cartItems.isNotEmpty()) {
                        cartHasItem()
                        for (cartItem in cartItems) {
                            cartList.add(
                                Cart(
                                    id = null,
                                    productId = cartItem.productId,
                                    productName = cartItem.productName,
                                    productImage = cartItem.productImage,
                                    productPrice = cartItem.productPrice,
                                    quantity = cartItem.quantity
                                )
                            )
                            totalAmount += cartItem.productPrice * cartItem.quantity
                        }
                        val adapter =
                            CartItemAdapter(view, cartList, ::addItemListener, ::removeItemListener)
                        val svc = Services()
                        binding.rvFtCartList.adapter = adapter
                        binding.rvFtCartList.layoutManager = LinearLayoutManager(requireContext())
                        binding.tvFtCartTotal.text = svc.formatPrice(totalAmount)
                    }
                }
            } catch (e: Exception) {
                Log.e("CartFragment", "Error: ${e.message}", e)
            }
        }
    }

    /**
     * Método llamado cuando se agrega un elemento al carrito.
     */
    fun addItemListener(item: Cart) {
        // Lanzar la llamada a deleteFromCart en un nuevo CoroutineScope
        CoroutineScope(Dispatchers.Main).launch {
            cartSvc.addToCart(item.asProduct())
        }
    }

    /**
     * Método llamado cuando se elimina un elemento del carrito.
     */
    fun removeItemListener(item: Cart) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val deleted = cartSvc.deleteFromCart(item)
                val cartItems = cartSvc.allProductsCart.first()
                if (deleted && cartItems.isEmpty()) {
                    cartIsEmpty()
                    requireActivity().recreate() // Recarga el fragment
                }
            } catch (e: Exception) {
                Log.e("Error", "Error en removeItemListener: $e")
            }
        }
    }

    /**
     * Método llamado cuando el carrito está vacío.
     */
    private fun cartIsEmpty() {
        binding.tvFtCartCartTitle.visibility = GONE
        binding.tvFrCartTotalTitle.visibility = GONE
        binding.tvFtCartTotal.visibility = GONE
        // emptyCartMessage visible
        binding.llFtCartEmptyCart.visibility = VISIBLE
    }

    /**
     * Método llamado cuando el carrito contiene elementos.
     */
    fun cartHasItem() {
        binding.tvFtCartCartTitle.visibility = VISIBLE
        binding.tvFrCartTotalTitle.visibility = VISIBLE
        binding.tvFtCartTotal.visibility = VISIBLE
        // emptyCartMessage hidden
        binding.llFtCartEmptyCart.visibility = GONE
    }
}
