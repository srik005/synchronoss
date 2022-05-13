package com.srikanth.synchronoss.retrofit

import androidx.lifecycle.LiveData
import com.srikanth.synchronoss.model.Weather
import com.srikanth.synchronoss.roomdb.WeatherDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(
    private val retrofitService: RetrofitService,
    private val weatherDao: WeatherDao
) {
    // val liveData = MutableLiveData<Weather>()

    fun getAllRecords(): LiveData<Weather> {
        return weatherDao.getData()
    }


    fun makeApiCall(lat: String, long: String, appID: String) {
        val call = retrofitService.getData(lat, long, appID)
        call.enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if (response.isSuccessful) {
                    response.body()?.weather?.forEach { _ ->
                        weatherDao.insertData(response.body()!!)
                    }

                }

            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}