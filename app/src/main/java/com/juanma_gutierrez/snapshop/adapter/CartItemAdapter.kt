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

/**
 * Adaptador personalizado para la lista de elementos del carrito.
 * @param context El contexto de la vista que utiliza este adaptador.
 * @param cartList La lista de elementos del carrito que se mostrarán.
 * @param addItemListener Un lambda que se llama cuando se hace clic en el botón de añadir para un elemento del carrito.
 * @param removeItemListener Un lambda que se llama cuando se hace clic en el botón de eliminar para un elemento del carrito.
 */
class CartItemAdapter(
    val context: View,
    val cartList: List<Cart>,
    val addItemListener: (item: Cart) -> Unit, // Agrega un listener para añadir productos
    val removeItemListener: (item: Cart) -> Unit // Agrega un listener para eliminar productos
) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    /**
     * Representa una vista de elemento del carrito.
     * @param itemVew La vista de elemento del carrito.
     */
    inner class ViewHolder(itemVew: View) : RecyclerView.ViewHolder(itemVew) {
        var ivItemImage: ImageView = itemView.findViewById(R.id.iv_cart_image)
        var tvName: TextView = itemView.findViewById(R.id.tv_cart_item_name)
        var tvQuantity: TextView = itemView.findViewById(R.id.tv_cart_quantity_item)
        var tvAmount: TextView = itemView.findViewById(R.id.tv_cart_amount_item)
        var btRemove: Button = itemView.findViewById(R.id.bt_cart_remove)
        var btAdd: Button = itemView.findViewById(R.id.bt_cart_add)
    }

    /**
     * Crea una nueva instancia de [ViewHolder] inflando el diseño para un elemento del carrito.
     * @param parent El grupo de vista padre al que se añadirá la nueva vista.
     * @param viewType El tipo de la nueva vista.
     * @return Una nueva instancia de [ViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_product_item, parent, false)
        return ViewHolder(v)
    }

    /**
     * Devuelve la cantidad total de elementos en la lista del carrito.
     * @return La cantidad total de elementos en la lista del carrito.
     */
    override fun getItemCount(): Int {
        return cartList.size
    }

    /**
     * Vincula los datos de un elemento del carrito con las vistas en la posición dada.
     * @param holder El [ViewHolder] que representa la vista del elemento del carrito.
     * @param position La posición del elemento en la lista del carrito.
     */
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


