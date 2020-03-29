package com.cmlteam.covidapp.validator.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.cmlteam.covidapp.activities.MainActivity
import com.cmlteam.covidapp.validator.dto.Slot
import com.example.covid_app.R
import com.example.demoappdrawermenu.service.HttpService
//import com.cmlteam.covidapp.validator.R
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime

class ScannerFragment: androidx.fragment.app.Fragment(), ZXingScannerView.ResultHandler {

    private lateinit var qrView: ZXingScannerView
    private lateinit var listener: QrScannerListener
    lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_scan, container, false)
        qrView = view.findViewById(R.id.qrReaderView)
        progressBar = view.findViewById(R.id.progress_bar)
        restartCamera()
        return view
    }

    override fun onResume() {
        super.onResume()
        qrView.stopCamera()
        qrView.setResultHandler(this)
        qrView.startCamera()
    }

    private fun restartCamera() {
        hideLoading()
        qrView.stopCamera()
        qrView.setResultHandler(this)
        qrView.startCamera()
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun handleResult(p0: Result?) {
        val qrResult = p0 ?: return
        val resultText = qrResult.text
        val userId = Integer.parseInt(resultText)
        showLoading()
        val mainActivity = activity as MainActivity

        HttpService.create().getSlotReservations(userId, MainActivity.placeId)
            .enqueue(object : Callback<List<Slot>> {
            override fun onFailure(call: Call<List<Slot>>, t: Throwable) {
                println("Error: $t")
                mainActivity.showReservationCheckResult(userId, null)
            }

            override fun onResponse(
                call: Call<List<Slot>>,
                response: Response<List<Slot>>
            ) {
                println("User slots received")
                val userSlots = response.body()
                val upcomingSlots = userSlots?.filter {slot ->
                    val now = LocalDateTime.now()
                    val slotDateTime = LocalDateTime.parse(slot.startDate)
                    slotDateTime.toLocalDate() === now.toLocalDate() &&
                            (slotDateTime.hour*60 + slotDateTime.minute - now.hour*60 - now.minute > -30)
                }
                mainActivity.showReservationCheckResult(userId, upcomingSlots)
                hideLoading()
            }
        })


    }

    companion object {

    }

    interface QrScannerListener {
        //fun openContent(content: Content)
        fun openContentId(contentId: String)
        fun openLocId(locId: String)
    }
}