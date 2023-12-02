package com.juanma_gutierrez.snapshop.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.databinding.CartProductItemBinding
import com.juanma_gutierrez.snapshop.services.CartItem

class CartItemAdapter(val context: View, val cartList: List<CartItem>) :
    RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemVew: View) : RecyclerView.ViewHolder(itemVew) {
        var ivItemImage: ImageView = itemView.findViewById(R.id.iv_cart_image)
        var tvName: TextView = itemView.findViewById(R.id.tv_cart_item_name)
        var tvQuantity: TextView = itemView.findViewById(R.id.tv_cart_quantity_item)
        var tvAmount: TextView = itemView.findViewById(R.id.tv_cart_amount_item)
        var btRemove: Button = itemView.findViewById(R.id.bt_cart_remove)
        var btAdd: Button = itemView.findViewById(R.id.bt_cart_add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_product_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: CartItem = cartList.get(position)
        holder.tvName.text = item.product.title
        holder.btRemove.setOnClickListener {
            Log.d("testing", "Elimina 1 elemento")
        }
        holder.btAdd.setOnClickListener {
            Log.d("testing", "Añade 1 elemento")
        }
    }
}