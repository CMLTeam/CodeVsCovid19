package com.cmlteam.covidapp.activity

import android.os.AsyncTask
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.cmlteam.covidapp.dto.Customer
import com.example.covid_app.R
import com.example.demoappdrawermenu.service.HttpService
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ProfileActivity : AppCompatActivity() {

    private lateinit var toolbar: ActionBar
    private lateinit var customer: Customer
//    private val getCustomerTask: GetCustomerTask = GetCustomerTask()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setupToolbar()
        initCustomer()


    }

    private fun initCustomer(){
//        getCustomerTask.execute(null as Void?)
        customer = Customer(1001,
            "+123567655656",
            "ID-53453",
            "John Doe",
            960,
            "normal",
            "Prenzlauer Allee 248-251, 10405 Berlin, Germany",
            "https://pickaface.net/gallery/avatar/Garret22785730d3a8d5525.png",
            arrayListOf(1002),
            arrayListOf())

        profile_name.text = customer.name
        profile_document_id.text = customer.documentId
        profile_score.text = "${customer.illnessRate} / 1000"
        if(customer.illnessRate < 200) findViewById<TextView>(R.id.profile_score).setTextColor(getColor(R.color.good_illness_rate))
        else if(customer.illnessRate > 200 && customer.illnessRate < 600) findViewById<TextView>(R.id.profile_score).setTextColor(getColor(R.color.normal_illness_rate))
        else if(customer.illnessRate > 600) findViewById<TextView>(R.id.profile_score).setTextColor(getColor(R.color.bad_illness_rate))
        profile_address.text = customer.address
        profile_number.text = customer.phoneNumber
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        toolbar = supportActionBar!!
        toolbar.title = getString(R.string.profile_toolbar_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
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
                            println("ADDRESS ADDRESS ADDRESS ADDRESS ADDRESS ADDRESS ADDRESS ${customer.address}")

                        }})

            }catch (e: InterruptedException) {
                System.err.print("Error: $e");
                return false
            }

            return true
        }

    }
}