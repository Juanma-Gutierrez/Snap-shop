package com.juanma_gutierrez.snapshop.ui.filter

class FilterService {
    var category: String? = null
    var minPrice: Int = 0
    var maxPrice: Int = Int.MAX_VALUE
    var rating: Int = 0

    fun resetFilter() {
        this.category = null
        this.minPrice = 0
        this.maxPrice = Int.MAX_VALUE
        this.rating = 0
    }
}