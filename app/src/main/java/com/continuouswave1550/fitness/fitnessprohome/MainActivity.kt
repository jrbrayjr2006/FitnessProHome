package com.continuouswave1550.fitness.fitnessprohome

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.continuouswave1550.fitness.fitnessprohome.databinding.ActivityMainBinding
import com.continuouswave1550.fitness.fitnessprohome.myfitness.MyFitnessFragment

class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.navigationHostFragment)
        //TODO add code to use navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        val navController = this.findNavController(R.id.navigationHostFragment)
        return navController.navigateUp()
    }

}

// Extension function to replace fragment
fun AppCompatActivity.replaceFragment(fragment:Fragment){
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(R.id.mainContainer,fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}