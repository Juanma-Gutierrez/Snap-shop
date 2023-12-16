package com.juanma_gutierrez.snapshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.data.repository.Cart
import com.juanma_gutierrez.snapshop.services.Services


class CartItemAdapter(
    val context: View,
    val cartList: List<Cart>,
    val addItemListener:(item:Cart) -> Unit, // Agrega un listener para añadir productos
    val removeItemListener: (item: Cart) -> Unit // Agrega un listener para eliminar productos
) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

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
        val svc = Services()   // Carga Services para el formato del precio
        val item: Cart = cartList.get(position)  // Carga el item
        val itemTotalPrice = item.productPrice * item.quantity // Calcula el precio total del item
        // Inserta el nombre, el total de precio, imágenes, ...
        holder.tvName.text = item.productName
        holder.tvQuantity.text = item.quantity.toString()
        holder.tvAmount.text = svc.formatPrice(itemTotalPrice)
        Glide.with(holder.itemView.context)
            .load(item.productImage)
            .into(holder.ivItemImage)
        holder.tvQuantity.text = item.quantity.toString()
        // Añade elementos al carrito
        holder.btAdd.setOnClickListener {
            addItemListener.invoke(item)
        }
        // Elimina el elemento del carrito
        holder.btRemove.setOnClickListener {
            removeItemListener.invoke(item)
        }
    }
}


