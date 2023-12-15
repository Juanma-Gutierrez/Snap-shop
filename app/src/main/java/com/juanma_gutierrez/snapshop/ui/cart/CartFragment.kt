package com.juanma_gutierrez.snapshop.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanma_gutierrez.snapshop.databinding.FragmentCartBinding
import com.juanma_gutierrez.snapshop.services.Services

// TODO Comentarios
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    // private lateinit var cartSvc: CartService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        cartSvc = CartService().getInstance()
        val svc = Services()
        if (cartSvc.getSize() != 0) {
            // Carga el recyclerView con la lista en cartList
            val adapter = CartItemAdapter(view, cartSvc.getCartList())
            cartHasItem()
            binding.rvCartList.adapter = adapter
            binding.rvCartList.layoutManager = LinearLayoutManager(requireContext())
            binding.tvCartTotal.text = svc.formatPrice(cartSvc.getAmount())
        } else {
            isEmpty()
        }
    }
    fun cartHasItem() {
        binding.tvCartTotalTitle.visibility = VISIBLE
        binding.tvCartTotal.visibility = VISIBLE
        binding.tvCartFragmentTitle.visibility = VISIBLE
        // emptyCartMessage hidden
        binding.llEmptyCart.visibility = GONE
    }

    fun isEmpty() {
        binding.tvCartTotalTitle.visibility = GONE
        binding.tvCartTotal.visibility = GONE
        binding.tvCartFragmentTitle.visibility = GONE
        // emptyCartMessage showed
        binding.llEmptyCart.visibility = VISIBLE
    }
         */
    }
}