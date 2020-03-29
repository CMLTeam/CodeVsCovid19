package com.cmlteam.covidapp.validator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cmlteam.covidapp.validator.dto.BookSlotRequest
import com.cmlteam.covidapp.validator.dto.Slot
import com.example.covid_app.R
import com.example.demoappdrawermenu.service.HttpService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationFragment(private val userId: Int, private val reservedSlots: List<Slot>?) : Fragment() {

    private lateinit var reservationFoundTextView: TextView
    private lateinit var reservationNotFoundTextView: TextView
    private lateinit var confirmButton: Button
    private lateinit var checkImageView: ImageView
    private lateinit var reservationDescriptionTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_reservation, container, false)

        reservationFoundTextView = view.findViewById(R.id.reservation_found)
        reservationNotFoundTextView = view.findViewById(R.id.reservation_not_found)
        reservationDescriptionTextView = view.findViewById(R.id.reservation_description)
        confirmButton = view.findViewById(R.id.confirm_button)
        checkImageView = view.findViewById(R.id.check_image)


        if (reservedSlots !== null && reservedSlots.isNotEmpty()) {
            val sorted = reservedSlots.sortedBy { slot -> slot.startDate }

            reservationFoundTextView.visibility = View.VISIBLE
            reservationNotFoundTextView.visibility = View.GONE
            reservationDescriptionTextView.text = sorted[0].description
            confirmButton.visibility = View.VISIBLE


            confirmButton.setOnClickListener {
                confirmButton.isClickable = false
                confirmButton.alpha = 0.5f
                HttpService.create().confirmSlot(BookSlotRequest(userId, sorted[0].id))
                    .enqueue(object: Callback<Void> {
                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            confirmButton.isClickable = false //To change body of created functions use File | Settings | File Templates.
                            confirmButton.alpha = 1f
                        }

                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            confirmButton.visibility = View.GONE
                            checkImageView.visibility = View.VISIBLE
                        }

                    })
            }
        }


        return view
    }



}