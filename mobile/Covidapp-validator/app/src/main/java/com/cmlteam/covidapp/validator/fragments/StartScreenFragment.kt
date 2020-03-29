package com.cmlteam.covidapp.validator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cmlteam.covidapp.activities.MainActivity
import com.example.covid_app.R

class StartScreenFragment: Fragment(){

    private lateinit var scanImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_screen, container, false)

        scanImageView = view.findViewById<ImageView>(R.id.scan_image)
        scanImageView.setOnClickListener {
            (activity as MainActivity).openScanner()
        }

        return view
    }

}