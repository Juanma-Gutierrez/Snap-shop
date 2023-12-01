package com.juanma_gutierrez.snapshop.adapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.databinding.ProductItemBinding
import com.juanma_gutierrez.snapshop.services.Services

class ProductAdapter(
    private val dataset: List<Product>,
    val onDetail: ((product: Product, view: View) -> Unit),
) : RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {
    class ItemViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        var binding: ProductItemBinding
        val iv_image: ImageView
        val tv_name: TextView
        val tv_price: TextView

        init {
            binding = ProductItemBinding.bind(view)
            iv_image = binding.ivProductImage
            tv_name = binding.tvProductName
            tv_price = binding.tvProductPrice
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = dataset[position]
        val svc = Services()
        holder.tv_name.text = product.title
        holder.tv_price.text = svc.formatPrice(product.price).toString()
        Glide.with(holder.itemView.context)
            .load(product.image)
            .into(holder.iv_image)
        holder.itemView.setOnClickListener {
            Log.d("testing", "Click en ${product.title}")
            onDetail(product, it)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}