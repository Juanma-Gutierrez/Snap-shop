package com.juanma_gutierrez.snapshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.databinding.ActivityMainBinding
import com.juanma_gutierrez.snapshop.ui.products.ProductDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Encuentra el fragmento de NavHost en el diseño
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        // Obtiene el NavController desde el NavHostFragment
        navController = navHostFragment.navController
        // Asigna navegación a los botones del bottomBar
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_product_list -> navController.navigate(R.id.productListFragment)
                R.id.navigation_cart -> navController.navigate(R.id.cartFragment)
                R.id.navigation_user -> navController.navigate(R.id.userFragment)
            }
            true
        }
    }
}
