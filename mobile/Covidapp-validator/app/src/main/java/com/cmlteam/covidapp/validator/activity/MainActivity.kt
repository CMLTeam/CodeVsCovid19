package com.cmlteam.covidapp.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.cmlteam.covidapp.validator.dto.Slot
import com.cmlteam.covidapp.validator.fragments.*
import com.example.covid_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.demoappdrawermenu.service.HttpService

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        openFragment(StartScreenFragment())
    }


    override fun onBackPressed() {
       openFragment(StartScreenFragment())
    }


    fun showReservationCheckResult(userId: Int, slots: List<Slot>?) {
        openFragment(ReservationFragment(userId, slots))
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

    fun openScanner() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openFragment(ScannerFragment())
            return
        }

        this.requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CAMERA -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    openFragment(ScannerFragment())
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


    companion object {
        public val REQUEST_CAMERA = 1
        public val placeId: Int = 1
    }

}
