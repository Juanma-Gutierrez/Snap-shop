package com.juanma_gutierrez.snapshop.services

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
}