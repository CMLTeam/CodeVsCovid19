package com.cmlteam.covidapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cmlteam.covidapp.dto.Slot
import com.cmlteam.covidapp.dto.Target
import com.example.covid_app.R
import com.example.demoappdrawermenu.service.HttpService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SupermarketActivity : AppCompatActivity() {

    private lateinit var toolbar: ActionBar
    private var mSupermarketList: List<Target>? = listOf(
        Target(1, "shop", "Auchan", 500, 50, "City, Street, Building",
            45.1f, 45.2f, "Mon-Fri: 09:00-20:00 Sat-Sun: 09:00-17:00", "", listOf(
                Slot(1, 1,"Mon 30 Mar 09:00-09:30", "2020-30-03T09:00:00", "2020-30-03T09:30:00", 15)
            ))
    )
    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supermarket)

        setupToolbar()

        mRecyclerView = findViewById(R.id.markets_recycler_view) as RecyclerView
        setupRecyclerView(mRecyclerView)
//        setupMarketList()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView?) {

        HttpService.create().getPlaces()
            .enqueue(object : Callback<List<Target>> {
            override fun onFailure(call: Call<List<Target>>, t: Throwable) {
                println("Error: $t")
            }

            override fun onResponse(call: Call<List<Target>>, response: Response<List<Target>>) {
                mSupermarketList = response.body() as List<Target>
                if (recyclerView !== null && mSupermarketList !== null) {
                    mLayoutManager = LinearLayoutManager(recyclerView.context)
                    recyclerView.layoutManager = mLayoutManager
                    recyclerView.adapter = SupermarketRecyclerViewAdapter()
                }
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        toolbar = supportActionBar!!
        toolbar.title = getString(R.string.supermarkets_toolbar_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    private fun goToMarket(market: Target) {
        startActivity(Intent(this, SlotActivity::class.java).putExtra("targetId", market.id))
    }


    inner class SupermarketRecyclerViewAdapter :
        RecyclerView.Adapter<SupermarketRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.supermarket_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val market = mSupermarketList!![position]
            holder.marketName.text = market.name
            holder.marketAddress.text = market.address
            holder.marketCapacity.text = "max: " + market.maxPeopleCapacity.toString()
            holder.marketHours.text = market.workingTime
            holder.marketDistance.text = ((market.distance)/1000.0f).toString() + " km"
            holder.availableToday.text = "12"
            holder.availableTomorrow.text = "17"

            Glide.with(this@SupermarketActivity)
                .load(market.pictureUrl)
                .dontAnimate()
                .fitCenter()
                .into(holder.marketImage)

            holder.mView.setOnClickListener {
                goToMarket(market)
            }

        }

        override fun getItemCount(): Int {
            return mSupermarketList!!.count()
        }

        inner class ViewHolder(val mView: View) :
            RecyclerView.ViewHolder(mView) {
            val marketName = mView.findViewById(R.id.market_name) as TextView
            val marketAddress = mView.findViewById(R.id.market_address) as TextView
            val marketCapacity = mView.findViewById(R.id.market_capacity) as TextView
            val marketDistance = mView.findViewById(R.id.market_distance) as TextView
            val marketImage = mView.findViewById(R.id.market_image) as ImageView
            val marketHours = mView.findViewById(R.id.working_hours) as TextView
            val availableToday = mView.findViewById(R.id.today_available) as TextView
            val availableTomorrow = mView.findViewById(R.id.tomorrow_available) as TextView
        }

    }

}