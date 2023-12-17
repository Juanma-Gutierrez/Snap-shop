package com.juanma_gutierrez.snapshop.ui.filter

object FilterService {
    var category: String = "none"
        private set
    var maxPrice: Int = Int.MAX_VALUE
        private set
    var rating: Float = 0.0f
        private set

    fun resetFilter() {
        this.setFilterCategory("none")
        this.setFilterMaxPrice(Int.MAX_VALUE)
        this.setFilterRating(0.0f)
    }

    fun setFilterCategory(category: String) {
        this.category = category
    }

    fun setFilterMaxPrice(maxPrice: Int) {
        this.maxPrice = maxPrice
    }

    fun setFilterRating(rating: Float) {
        this.rating = rating
    }
}