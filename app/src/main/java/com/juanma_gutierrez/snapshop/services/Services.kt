package com.juanma_gutierrez.snapshop.services

import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.Locale

/**
 * Clase que proporciona servicios diversos.
 */
class Services {
    /**
     * Formatea el precio en formato de moneda según la configuración regional.
     * @param price El precio a formatear.
     * @return El precio formateado como una cadena de moneda.
     */
    fun formatPrice(price: Double): String {
        val locale = Locale.getDefault()
        val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
        return currencyFormatter.format(price)
    }

    fun showSnackbar(message: String, view: View) {
        val snackbar = Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_SHORT
        )
        val layoutParams = snackbar.view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(
            layoutParams.leftMargin,
            layoutParams.topMargin,
            layoutParams.rightMargin,
            250
        )
        snackbar.view.layoutParams = layoutParams

        snackbar.show()
    }
}