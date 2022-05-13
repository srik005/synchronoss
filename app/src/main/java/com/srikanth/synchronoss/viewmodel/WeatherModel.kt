package com.srikanth.synchronoss.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srikanth.synchronoss.retrofit.RetroRepository
import com.srikanth.synchronoss.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherModel @Inject constructor(
    private val retroRepo: RetroRepository,
) : ViewModel() {


    fun getWeatherData(): MutableLiveData<Weather> {
        return retroRepo.getAllRecords()

    }

    fun getApi(mLat: String, mLong: String, key: String) {
        retroRepo.makeApiCall(mLat, mLong, key)
    }
}