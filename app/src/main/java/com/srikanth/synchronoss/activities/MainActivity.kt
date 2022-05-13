package com.srikanth.synchronoss.activities

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.srikanth.synchronoss.adapter.WeatherAdapter
import com.srikanth.synchronoss.databinding.ActivityMainBinding
import com.srikanth.synchronoss.model.Weather
import com.srikanth.synchronoss.retrofit.RetroRepository
import com.srikanth.synchronoss.viewmodel.WeatherModel
import dagger.hilt.android.AndroidEntryPoint

private const val PERMISSION_CODE = 2001
private const val API_KEY = "94710e0e42f57391075ff4b23a353cf2"

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LocationListener {
    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: WeatherAdapter
    private lateinit var mLat: String
    private lateinit var mLong: String
    private val viewModel: WeatherModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkLoc()
        initViewModel()

    }


    private fun initViewModel() {

        recyclerAdapter = WeatherAdapter()
        binding.recyclerView.adapter = recyclerAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerView.setHasFixedSize(true)

        viewModel.getWeatherData().observe(this) {
            recyclerAdapter.setItems(it.weather)
            recyclerAdapter.notifyDataSetChanged()
        }
    }

    private fun checkLoc() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_CODE
            )
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }

    override fun onLocationChanged(location: Location) {
        d("lat", location.latitude.toString())
        d("long", location.longitude.toString())
        viewModel.getApi(location.latitude.toString(),location.longitude.toString(), API_KEY)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}