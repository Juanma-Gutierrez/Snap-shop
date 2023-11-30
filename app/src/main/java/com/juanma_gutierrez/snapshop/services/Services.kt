package com.juanma_gutierrez.snapshop.services

import java.text.NumberFormat
import java.util.Locale

class Services {
    fun formatPrice(price: Double): String {
        val locale = Locale.getDefault()
        val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
        return currencyFormatter.format(price)
    }
}