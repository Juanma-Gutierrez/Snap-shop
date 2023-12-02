package com.juanma_gutierrez.snapshop.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.adapter.CartItemAdapter
import com.juanma_gutierrez.snapshop.adapter.ProductAdapter
import com.juanma_gutierrez.snapshop.databinding.FragmentCartBinding
import com.juanma_gutierrez.snapshop.databinding.FragmentProductDetailBinding
import com.juanma_gutierrez.snapshop.databinding.FragmentProductListBinding
import com.juanma_gutierrez.snapshop.services.CartItem
import com.juanma_gutierrez.snapshop.services.CartService

// TODO Comentarios
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartSvc:CartService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartSvc = CartService().getInstance()
        if (cartSvc.getSize() != 0) {

            // RecyclerView
            val adapter = CartItemAdapter(view,cartSvc.getCartList())
            binding.rvCartList.adapter = adapter
            binding.rvCartList.layoutManager=LinearLayoutManager(requireContext())
        } else {
            binding.tvCartItemList.text = "Carrito vacío"
        }
    }
}