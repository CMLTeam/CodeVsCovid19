package com.cmlteam.covidapp.fragment

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.cmlteam.covidapp.dto.Customer
import com.example.covid_app.R
import com.example.demoappdrawermenu.service.HttpService

import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var customer: Customer
    private val getCustomerTask: GetCustomerTask = GetCustomerTask()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initCustomer()
        return view

    }

    private fun initCustomer() {
        getCustomerTask.execute(null as Void?)
    }

    inner class GetCustomerTask internal constructor() :
        AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void): Boolean? {
            try {
                HttpService.create().getCustomerPage()
                    .enqueue(object : retrofit2.Callback<Customer> {
                        override fun onFailure(call: Call<Customer>, t: Throwable) {
                            println("Error $t")
                        }

                        override fun onResponse(
                            call: Call<Customer>,
                            response: Response<Customer>
                        ) {
                            customer = response.body()!!

                            if (profile_image == null) {
                                return
                            }

                            Glide.with(this@ProfileFragment.context)
                                .load(customer.pictureUrl)
                                .dontAnimate()
                                .placeholder(context!!.getDrawable(R.drawable.index)!!)
                                .dontTransform()
                                .fitCenter()
                                .into(profile_image)

//                            Picasso.get().load(customer.pictureUrl)
//                                .placeholder()
//                                .resize(700, 700)
//                                .centerCrop().into(profile_image);
                            profile_name.text = customer.name
                            profile_document_id.text = customer.documentId
                            profile_score.text = "${customer.illnessRate} / 1000"
                            when {
                                customer.illnessRate < 300 -> view!!.findViewById<TextView>(R.id.profile_score).setTextColor(
                                    context!!.getColor(R.color.bad_illness_rate)
                                )
                                customer.illnessRate in 301..699 -> view!!.findViewById<TextView>(R.id.profile_score).setTextColor(
                                    context!!.getColor(R.color.normal_illness_rate)
                                )
                                customer.illnessRate > 600 -> view!!.findViewById<TextView>(R.id.profile_score).setTextColor(
                                    context!!.getColor(R.color.good_illness_rate)
                                )
                            }
                            profile_address.text = customer.address
                            profile_number.text = customer.phoneNumber
                        }
                    })

            } catch (e: InterruptedException) {
                System.err.print("Error: $e");
                return false
            }

            return true
        }

    }

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }


}