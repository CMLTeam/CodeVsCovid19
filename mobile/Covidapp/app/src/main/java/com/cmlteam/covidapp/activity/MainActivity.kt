package com.cmlteam.covidapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.cmlteam.covidapp.activity.CustomerActivity
import com.cmlteam.covidapp.activity.SupermarketActivity
import com.example.covid_app.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        val profileButton = findViewById<ImageButton>(R.id.profile_button)
        val superMarketsButton = findViewById<ImageButton>(R.id.supermarkets_button)

        profileButton.setOnClickListener(this)
        superMarketsButton.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.profile_button -> startActivity(Intent(this, CustomerActivity::class.java))
            R.id.supermarkets_button -> startActivity(Intent(this, SupermarketActivity::class.java))
        }
    }

    override fun onBackPressed() {
    }

    private fun setupToolbar() {
        toolbar = supportActionBar!!
        toolbar.hide()
        toolbar.title = getString(R.string.main_menu_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }


}
