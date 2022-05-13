package com.srikanth.synchronoss.retrofit

import com.srikanth.synchronoss.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("weather")
    fun getData(
        @Query("lat") currLat: String,
        @Query("lon") currLong: String,
        @Query("appId") appId: String,
    ): Call<Weather>
}