package com.continuouswave1550.fitness.fitnessprohome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.continuouswave1550.fitness.fitnessprohome.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.navigationHostFragment)
        drawerLayout = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navigationDrawerLayout, navController)

        setupBottomNavMenu(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        val navController = this.findNavController(R.id.navigationHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav: BottomNavigationView = binding.bottomNavigation
        bottomNav?.setupWithNavController(navController)
    }

}

// Extension function to replace fragment
fun AppCompatActivity.replaceFragment(fragment:Fragment){
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(R.id.drawerLayout,fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}