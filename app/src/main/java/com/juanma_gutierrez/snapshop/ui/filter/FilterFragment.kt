package com.juanma_gutierrez.snapshop.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.databinding.FragmentCartBinding
import com.juanma_gutierrez.snapshop.databinding.FragmentFilterBinding
import com.juanma_gutierrez.snapshop.databinding.FragmentProductListBinding

class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding

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
        binding.tbFrFilterTopBar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.it_menuFilter_back -> {
                    Navigation.findNavController(view).navigate(R.id.action_filterFragment_to_productListFragment)
                    true
                }
                else -> false
            }
        }
    }
}