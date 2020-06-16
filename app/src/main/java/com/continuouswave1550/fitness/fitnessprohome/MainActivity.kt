package com.continuouswave1550.fitness.fitnessprohome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.continuouswave1550.fitness.fitnessprohome.myfitness.MyFitnessFragment

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(MyFitnessFragment())
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