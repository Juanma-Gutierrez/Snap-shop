package com.juanma_gutierrez.snapshop.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.ui.cart.CartViewModel
import com.juanma_gutierrez.snapshop.data.repository.Product
import com.juanma_gutierrez.snapshop.databinding.ProductItemBinding
import com.juanma_gutierrez.snapshop.services.Services
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Clase de adaptador para manejar la visualización de elementos de productos en un RecyclerView.
 * @param dataset La lista de productos a mostrar.
 * @param onDetail Función de devolución de llamada que se invoca al hacer clic en un producto.
 * @param cartSvc El servicio del carrito que gestiona las operaciones relacionadas con el carrito.
 */
class ProductAdapter(
    private val dataset: List<Product>,
    private val onDetail: ((product: Product, view: View) -> Unit),
    private val cartSvc: CartViewModel
) : RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {

    /**
     * Clase ViewHolder para contener las vistas de items de productos.
     * @param view La vista inflada para un item de producto.
     */
    class ItemViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        var binding: ProductItemBinding
        val iv_image: ImageView
        val tv_name: TextView
        val tv_price: TextView
        val iv_cart: ImageView

        init {
            binding = ProductItemBinding.bind(view)
            iv_image = binding.ivProductImage
            tv_name = binding.tvProductName
            tv_price = binding.tvProductPrice
            iv_cart = binding.ivProductItemCart
        }
    }

    /**
     * Crea un nuevo [ItemViewHolder] inflando el diseño para un item de producto.
     * @param parent El grupo de vista padre al que se añadirá la nueva vista.
     * @param viewType El tipo de la nueva vista.
     * @return Una nueva instancia de [ItemViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    /**
     * Vincula los datos de un producto en la posición dada al [ItemViewHolder].
     * @param holder El [ItemViewHolder] al que se vincularán los datos.
     * @param position La posición del producto en el conjunto de datos.
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = dataset[position]
        val svc = Services()
        holder.tv_name.text = product.title
        holder.tv_price.text = svc.formatPrice(product.price).toString()
        Glide.with(holder.itemView.context)
            .load(product.image)
            .into(holder.iv_image)
        holder.itemView.setOnClickListener {
            onDetail(product, it)
        }
        holder.iv_cart.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    cartSvc.addToCart(product)
                    svc.showSnackBar("Producto añadido al carrito", holder.itemView)
                } catch (e: Exception) {
                    Log.e("error", "Error al cargar el usuario", e)
                }
            }
        }

    }

    /**
     * Obtiene el número total de elementos en el conjunto de datos.
     * @return El número total de elementos en el conjunto de datos.
     */
    override fun getItemCount(): Int {
        return dataset.size
    }
}