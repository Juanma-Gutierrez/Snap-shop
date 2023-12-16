package com.juanma_gutierrez.snapshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Configurar la Toolbar y manejar clics en los elementos del menú
        binding.topTbToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                // R.id.top_tb_electronics -> showToast("1 ${this}")
                // R.id.top_tb_jewelry -> showToast("2")
                // R.id.top_tb_mens -> showToast("3")
                // R.id.top_tb_womens -> showToast("4")
            }
            true
        }
        // Encuentra el fragmento de NavHost en el diseño
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        // Obtiene el NavController desde el NavHostFragment
        navController = navHostFragment.navController
        val color = ContextCompat.getColor(this@MainActivity, R.color.md_theme_light_inversePrimary)
        binding.topTbToolbar.setBackgroundColor(color)
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
