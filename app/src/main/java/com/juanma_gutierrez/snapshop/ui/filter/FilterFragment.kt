package com.juanma_gutierrez.snapshop.ui.filter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    var filterSvc = FilterService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFilters()
        binding.tbFrFilterTopBar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.it_menuFilter_back -> {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_filterFragment_to_productListFragment)
                    true
                }
                else -> false
            }
        }
        binding.btFrFilterApplyButton.setOnClickListener {
            setFilter()
            Navigation.findNavController(view)
                .navigate(R.id.action_filterFragment_to_productListFragment)
        }
        binding.btFrFilterResetFilterButton.setOnClickListener {
            resetCategory()
            binding.sbFrFilterPrice.progress = 1000
            binding.rbFrFilterRatingStars.rating = 0.0f
        }
    }

    private fun initFilters() {
        binding.rbFrFilterWomen.isChecked = false
        binding.rbFrFilterMen.isChecked = false
        binding.rbFrFilterJewelery.isChecked = false
        binding.rbFrFilterElectronics.isChecked = false
        when (filterSvc.category) {
            "women's clothing" -> binding.rbFrFilterWomen.isChecked = true
            "men's clothing" -> binding.rbFrFilterMen.isChecked = true
            "jewelery" -> binding.rbFrFilterJewelery.isChecked = true
            "electronics" -> binding.rbFrFilterElectronics.isChecked = true
        }
        binding.sbFrFilterPrice.progress = filterSvc.maxPrice
        binding.rbFrFilterRatingStars.rating = filterSvc.rating
    }

    private fun resetCategory() {
        binding.rbFrFilterWomen.isChecked = false
        binding.rbFrFilterMen.isChecked = false
        binding.rbFrFilterJewelery.isChecked = false
        binding.rbFrFilterElectronics.isChecked = false
    }


    private fun setFilter() {
        val category = getCategory()
        val maxPrice = binding.sbFrFilterPrice.progress
        val rating = binding.rbFrFilterRatingStars.rating
        filterSvc.setFilterCategory(category)
        filterSvc.setFilterMaxPrice(maxPrice)
        filterSvc.setFilterRating(rating)
    }

    private fun getCategory(): String {
        var category = "none"
        if (binding.rbFrFilterWomen.isChecked) {
            category = "women's clothing"
        }
        if (binding.rbFrFilterMen.isChecked) {
            category = "men's clothing"
        }
        if (binding.rbFrFilterJewelery.isChecked) {
            category = "jewelery"
        }
        if (binding.rbFrFilterElectronics.isChecked) {
            category = "electronics"
        }
        return category
    }
}