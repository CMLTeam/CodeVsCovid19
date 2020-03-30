package com.cmlteam.covidapp.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cmlteam.covidapp.dto.BookSlotRequest
import com.cmlteam.covidapp.dto.Slot
import com.cmlteam.covidapp.dto.Target
import com.example.covid_app.R
import com.example.demoappdrawermenu.service.HttpService
import kotlinx.android.synthetic.main.supermarket_view.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class SlotActivity : AppCompatActivity() {

    private lateinit var toolbar: ActionBar

    private var mSlotList: List<Slot>? = null
    private var mTarget: Target? = null
    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var targetIdOnCreate: Int = 1
    private var marketNameTextView:TextView? = null
    private var marketAddressTextView:TextView? = null
    private var marketCapacityTextView:TextView? = null
    private var marketDistanceTextView:TextView? = null
    private var marketImageView:ImageView? = null
    private var marketHoursTextView:TextView? = null
    private var availableTodayTextView:TextView? = null
    private var availableTomorrowTextView:TextView? = null

    private var chooseSlotTextView: TextView? = null
    private var slotConfirmedHintTextView: TextView? = null
    private var slotConfirmedTextView: TextView? = null
    private var checkMark: ImageView? = null
    private var confirmButton: Button? = null

    private var activeSlot: Slot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slots)
        targetIdOnCreate = intent.getIntExtra("targetId", 1)

        setupToolbar()
        mRecyclerView = findViewById(R.id.slots_recycler_view) as RecyclerView

        marketNameTextView = findViewById(R.id.market_name) as TextView
        marketAddressTextView = findViewById(R.id.market_address) as TextView
        marketCapacityTextView = findViewById(R.id.market_capacity) as TextView
        marketDistanceTextView = findViewById(R.id.market_distance) as TextView
        marketImageView = findViewById(R.id.market_image) as ImageView
        marketHoursTextView = findViewById(R.id.working_hours) as TextView
        availableTodayTextView = findViewById(R.id.today_available) as TextView
        availableTomorrowTextView = findViewById(R.id.tomorrow_available) as TextView

        confirmButton = findViewById(R.id.reserve_button) as Button
        checkMark = findViewById(R.id.check_image) as ImageView
        chooseSlotTextView = findViewById(R.id.choose_slot_text) as TextView
        slotConfirmedTextView = findViewById(R.id.slot_reserved) as TextView
        slotConfirmedHintTextView = findViewById(R.id.reservation_hint) as TextView

        setupRecyclerView(mRecyclerView)


        confirmButton!!.setOnClickListener {
            HttpService.create().reserveSlot(1001, activeSlot!!.id)
                .enqueue(object: Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        println("Error: $t")
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        checkMark!!.visibility = View.VISIBLE
                        mRecyclerView!!.visibility = View.GONE
                        confirmButton!!.visibility = View.GONE
                        chooseSlotTextView!!.text = LocalDateTime.parse(activeSlot!!.startDate).toLocalDate().toString() +
                                " " + activeSlot!!.asString
                        slotConfirmedTextView!!.visibility = View.VISIBLE
                        slotConfirmedHintTextView!!.visibility = View.VISIBLE
                    }
                })
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView?) {

        HttpService.create().getPlaceSlots(targetIdOnCreate)
            .enqueue(object : Callback<Target> {
            override fun onFailure(call: Call<Target>, t: Throwable) {
                println("Error: $t")
            }

            override fun onResponse(call: Call<Target>, response: Response<Target>) {
                mTarget = response.body()
                mSlotList = mTarget?.slots
                showTargetInfo()

                if (recyclerView !== null && mSlotList !== null) {
                    mLayoutManager = LinearLayoutManager(recyclerView.context)
                    recyclerView.layoutManager = mLayoutManager
                    recyclerView.adapter = SlotsRecyclerViewAdapter()
                }
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun showTargetInfo() {

        val target = mTarget!!

        marketNameTextView!!.text = target.name
        marketAddressTextView!!.text = target.address
        marketCapacityTextView!!.text = "max: " + target.maxPeopleCapacity.toString()
        marketDistanceTextView!!.text = (target.distance/1000f).toString() + " km"
        marketHoursTextView!!.text = target.workingTime
        availableTodayTextView!!.text = "3"
        availableTomorrowTextView!!.text = "8"

        Glide.with(this@SlotActivity)
            .load(target.pictureUrl)
            .dontAnimate()
            .fitCenter()
            .into(marketImageView)
    }

    private fun setupToolbar() {
        toolbar = supportActionBar!!
        toolbar.title = getString(R.string.supermarkets_toolbar_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    private fun refreshSlotList() {
        mRecyclerView!!.adapter!!.notifyDataSetChanged();
        if (activeSlot !== null) {
            confirmButton!!.setBackgroundColor(Color.parseColor("#FF008577"))
            confirmButton!!.setTextColor(Color.WHITE)
        } else {
            confirmButton!!.background = resources.getDrawable(android.R.drawable.btn_default, null)
        }
    }

//    private fun goToMarket(market: Target) {
//        startActivity(Intent(this, SlotActivity::class.java).putExtra("targetId", market.id))
//    }

    inner class SlotsRecyclerViewAdapter :
        RecyclerView.Adapter<SlotsRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.slot_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val slot = mSlotList!![position]

            holder.slotCapacity.text = "max: " + mTarget!!.maxPeopleCapacity.toString()
            holder.capacityLeft.text = slot.freeCapacity.toString()
            holder.slotDate.text = LocalDateTime.parse(slot.startDate).toLocalDate().toString()
            holder.slotHours.text = slot.asString

            if (activeSlot?.id == slot.id) {
                holder.mView.setBackgroundColor(Color.parseColor("#6A00D69A"))
            } else {
                holder.mView.setBackgroundColor(Color.parseColor("#2000D69A"))
            }

            holder.mView.setOnClickListener {
                activeSlot = slot
                refreshSlotList()
            }

        }

        override fun getItemCount(): Int {
            return mSlotList!!.count()
        }

        inner class ViewHolder(val mView: View) :
            RecyclerView.ViewHolder(mView) {
            val slotCapacity = mView.findViewById(R.id.slot_capacity) as TextView
            val slotHours = mView.findViewById(R.id.slot_hours) as TextView
            val slotDate = mView.findViewById(R.id.slot_date) as TextView
            val capacityLeft = mView.findViewById(R.id.places_left) as TextView
        }

    }

}