package com.srikanth.synchronoss.model

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity(tableName = "weatherDB")
data class Weather(
    val cod: Int,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")val weatherID: Int,
    @Embedded
    val weather: List<WeatherList>?,
    @Embedded
    val main: Temperature?
){
    constructor():this(0,0,null,null)
}


data class WeatherList(
    @SerializedName("id")val listID:String,
    val main: String,
    val description: String
)


data class Temperature(
    val temp: String
)

class TypeConverterWeather {
    val gson : Gson = Gson()
    @TypeConverter
    fun stringToList(data: String) : List<Weather>? {
        val listType: Type = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson<List<Weather>>(data, listType)
    }

    @TypeConverter
    fun listToString(someobject: List<Weather>): String
    {
        return gson.toJson(someobject)
    }
}