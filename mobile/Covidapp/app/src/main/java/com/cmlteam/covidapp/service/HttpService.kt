package com.example.demoappdrawermenu.service

import com.cmlteam.covidapp.dto.BookSlotRequest
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.sql.Time
import java.util.concurrent.TimeUnit

interface HttpService {

    @GET("/customers/{id}")
    fun getUser(
        @Path("id") id: String
    ): Call<Any>//User>


    @GET("/targets")
    fun getPlaces(
    ): Call<Any>//List<Places>


    @GET("/target/slots")
    fun getPlaceSlots(
        @Query("targetId") targetId: String
    ) : Call<Any>//Place&Slots

    @POST("/bookings")
    fun reserveSlot(
        @Body bookSlotRequest: BookSlotRequest
    ): Call<Void>

    @GET("/bookings")
    fun getReservedSlots(): Call<Any>//List<Place&Slots>

    /* Returns ReservationValidationResult - {validated: true/false; reservations: list of reservations} */
    @GET("/customers/{customer_id}/slots/{target_id}")
    fun validateSlotReservation(
        @Path("customer_id") userId: String,
        @Path("target_id") placeId: String
    ): Call<Any>//ReservationValidationResult>

    @POST("/confirm")
    fun reserveNow(
        @Body bookSlotRequest: BookSlotRequest
    )

    @GET("customers/{customer_id}/related")
    fun getRelatedUsers(
        @Path("customer_id") userId: String
    )



//    @POST("/auth/sign-up")
//    fun signUp(
//        @Body signUpRequest: SignUpAccountRequest
//    ):Call<Void>
//

    companion object {
        fun create(): HttpService {

            val client = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://cmlteam.com:38080")
                .client(client)
                .build()

            return retrofit.create(HttpService::class.java)
        }
    }
}

