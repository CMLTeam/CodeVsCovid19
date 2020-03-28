package com.cmlteam.covidapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.covid_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.ProfileFragment
import fragments.ShopFragment

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        setupProfile()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_bar)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_profile -> {
                    setupProfile()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_shops -> {
                    setupShops()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

    }


    override fun onBackPressed() {
    }


    private fun setupProfile(){
        toolbar.title = applicationContext.getString(R.string.bottom_bar_profile)
        val profileFragment = ProfileFragment.newInstance()
        openFragment(profileFragment)
    }

    private fun setupShops(){
        toolbar.title = applicationContext.getString(R.string.bottom_bar_shops)
        val shopsFragment = ShopFragment.newInstance()
        openFragment(shopsFragment)
    }


    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()
    }








}
