package com.cmlteam.covidapp.validator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.covid_app.R
import com.example.demoappdrawermenu.service.HttpService

class ShopFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shops, container, false)
        view.findViewById<TextView>(R.id.reservation_found).text = "Shops is here"

        return view
    }

    companion object {
        private var httpService: HttpService? = null

        fun newInstance(): ShopFragment {
            if (httpService == null) {
                httpService = HttpService.create()
            }
            return ShopFragment()
        }
    }

}