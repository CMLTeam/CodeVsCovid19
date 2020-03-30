package com.cmlteam.covidapp.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cmlteam.covidapp.fragment.ProfileFragment
import com.cmlteam.covidapp.fragment.QRFragment
import com.example.covid_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomerActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)

        setupToolBar()
        setupProfileFragment()
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_bar)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_profile -> {
                    setupProfileFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_qr -> {
                    setupQrFragment()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }


    }

    private fun setupToolBar() {
        toolbar = supportActionBar!!
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }


    private fun setupProfileFragment() {
        toolbar.title = getString(R.string.bottom_bar_profile)
        val profileFragment = ProfileFragment.newInstance()
        openFragment(profileFragment)
    }

    private fun setupQrFragment() {
        toolbar.title = getString(R.string.bottom_bar_QR)
        val qrFragment = QRFragment.newInstance()
        openFragment(qrFragment)
    }


    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()
    }


}