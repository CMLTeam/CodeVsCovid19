package com.example.demoappdrawermenu.service

import com.example.demoappdrawermenu.model.BusStopsResponse
import com.example.demoappdrawermenu.model.RouteDtoResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.sql.Time
import java.util.concurrent.TimeUnit

interface HttpService {

    @GET("/routes")
    fun getRoutes(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
        @Query("duration") duration: Int = 20
    ): Call<List<RouteDtoResponse.RouteDto>>

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

