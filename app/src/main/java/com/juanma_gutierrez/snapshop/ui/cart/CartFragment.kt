package com.juanma_gutierrez.snapshop.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanma_gutierrez.snapshop.adapter.CartItemAdapter
import com.juanma_gutierrez.snapshop.data.repository.Cart
import com.juanma_gutierrez.snapshop.data.repository.CartService
import com.juanma_gutierrez.snapshop.databinding.FragmentCartBinding
import com.juanma_gutierrez.snapshop.services.Services
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO Comentarios
@AndroidEntryPoint
class CartFragment : Fragment() {
    @Inject
    lateinit var cartSvc: CartService
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            try {
                cartSvc.allProductsCart.collect { cartItems ->
                    val cartList: MutableList<Cart> = mutableListOf()
                    var totalAmount = 0.0
                    // Recorro toda el carrito y lo añado a cartList
                    if (cartItems.size != 0) {
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
                            Log.d(
                                "testing",
                                " ${cartItem.productName} ${cartItem.productPrice} ID: ${cartItem.productId}, Quantity: ${cartItem.quantity}"
                            )
                        }
                        val adapter = CartItemAdapter(view, cartList, ::addItemListener, ::removeItemListener)
                        cartHasItem()
                        val svc = Services()
                        binding.rvCartList.adapter = adapter
                        binding.rvCartList.layoutManager = LinearLayoutManager(requireContext())
                        binding.tvCartTotal.text = svc.formatPrice(totalAmount)
                    }
                }
            } catch (e: Exception) {
                Log.e("CartFragment", "Error: ${e.message}", e)
            }
        }

    }

    fun addItemListener(item: Cart) {
        // Lanzar la llamada a deleteFromCart en un nuevo CoroutineScope
        CoroutineScope(Dispatchers.Main).launch {
            cartSvc.addToCart(item.asProduct())
        }
    }
    fun removeItemListener(item: Cart) {
        // Lanzar la llamada a deleteFromCart en un nuevo CoroutineScope
        CoroutineScope(Dispatchers.Main).launch {
            cartSvc.deleteFromCart(item)
        }
    }

    fun cartHasItem() {
        binding.tvCartTotalTitle.visibility = VISIBLE
        binding.tvCartTotal.visibility = VISIBLE
        binding.tvCartFragmentTitle.visibility = VISIBLE
        // emptyCartMessage hidden
        binding.llEmptyCart.visibility = GONE
    }
}
