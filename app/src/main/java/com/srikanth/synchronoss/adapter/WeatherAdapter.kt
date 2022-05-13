package com.srikanth.synchronoss.adapter

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srikanth.synchronoss.databinding.ActivityDetailsBinding
import com.srikanth.synchronoss.model.Weather
import com.srikanth.synchronoss.model.WeatherList


//class WeatherAdapter() : ListAdapter<WeatherList, WeatherAdapter.WeatherHolder>(WeatherUtilCallBack()) {

class WeatherAdapter :
    RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {
    private var list: List<WeatherList>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherHolder {
        val mView =
            ActivityDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return WeatherHolder(mView)
    }


    override fun onBindViewHolder(
        holder: WeatherHolder,
        position: Int
    ) {
        holder.bind(list?.get(position)!!)

    }

    /* override fun getItemCount(): Int {
         d("size", dataList.size.toString())
         return dataList.size
     }*/


    class WeatherHolder(private val binding: ActivityDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherData: WeatherList) {
            binding.tvWeather.text = weatherData.description
            binding.tvTemperature.text = weatherData.main
        }
    }

    /* class WeatherUtilCallBack : DiffUtil.ItemCallback<WeatherList>() {
         override fun areItemsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
             return oldItem == newItem
         }

         override fun areContentsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
             return oldItem == newItem
         }
     }
 */
    override fun getItemCount(): Int {
        d("list size", list?.size.toString())
        if (list == null) return 0
        else
            return list!!.size

    }

    fun setItems(wList: List<WeatherList>?) {
        list = wList
        d("listItem", wList.toString())
    }
}
