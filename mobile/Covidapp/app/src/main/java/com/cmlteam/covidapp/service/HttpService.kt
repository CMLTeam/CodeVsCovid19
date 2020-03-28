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

    @GET("/user/{id}")
    fun getUser(
        @Path("id") id: String

    ): Call<Any>//User>


    @GET("/target")
    fun getPlaces(
    ): Call<Any>//List<Places>


    @GET("/target/{target_id}")
    fun getPlace(
        @Path("target_id") id: String
    ) : Call<Any>//Place

    @POST("/reserve")
    fun reserveSlot(
        @Body bookSlotRequest: BookSlotRequest
    ): Call<Void>

    @GET("/reserve/{user_id}/{target_id}")
    fun getReservedSlots(
        @Path("user_id") userId: String,
        @Path("target_id") placeId: String
    ):Call<Any>//List<Reservation>

//    @POST("/auth/sign-up")
//    fun signUp(
//        @Body signUpRequest: SignUpAccountRequest
//    ):Call<Void>
//

    companion object {
        fun create(accessToken:String = ""): HttpService {

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

