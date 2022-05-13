package com.srikanth.synchronoss.roomdb

import android.content.Context
import androidx.room.*
import com.srikanth.synchronoss.model.TypeConverterWeather
import com.srikanth.synchronoss.model.Weather

@Database(entities = [Weather::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterWeather::class)
abstract class WeatherDB : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        private var instance: WeatherDB? = null

        fun getDatabase(context: Context): WeatherDB {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context, WeatherDB::class.java,
                    "weatherDB"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}