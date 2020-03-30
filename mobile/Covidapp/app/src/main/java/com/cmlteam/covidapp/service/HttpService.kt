package com.example.demoappdrawermenu.service

import com.cmlteam.covidapp.dto.BookSlotRequest
import com.cmlteam.covidapp.dto.Customer
import com.cmlteam.covidapp.dto.Slot
import com.cmlteam.covidapp.dto.Target
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.sql.Time
import java.util.concurrent.TimeUnit

interface HttpService {

    @GET("/me")
    fun getCustomerPage():Call<Customer>


    @GET("/targets")
    fun getPlaces(
    ): Call<List<Target>>


    @GET("/targets/{target_id}/slots")
    fun getPlaceSlots(
        @Path("target_id") targetId: Int
    ) : Call<Target>

    @POST("/bookings")
    fun reserveSlot(
        @Query("customerId") userId: Int,
        @Query("slotId") slotId: Int
        //@Body bookSlotRequest: BookSlotRequest  // {customer_id, slot_id? or slot_date_time?}
    ): Call<Void>

    @GET("/bookings")
    fun getReservedSlots(): Call<List<Target>>

    /* Returns ReservationValidationResult - {validated: true/false; reservations: list of reservations} */
    @GET("/customers/{customer_id}/bookings/{target_id}")  //for a security app
    fun getSlotReservations(
        @Path("customer_id") userId: String,
        @Path("target_id") placeId: String
    ): Call<List<Slot>>

    @POST("/confirm")
    fun confirmSlot(
        @Body bookSlotRequest: BookSlotRequest    // {customer_id, slot_id? or slot_date_time?}
    ) : Call<Void>

    @GET("customers/{customer_id}/related")
    fun getRelatedUsers(
        @Path("customer_id") userId: Int
    ) : Call<List<Int>>


    companion object {
        fun create(): HttpService {

            val client = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://cmlteam.com:8099")
                .client(client)
                .build()

            return retrofit.create(HttpService::class.java)
        }
    }
}

