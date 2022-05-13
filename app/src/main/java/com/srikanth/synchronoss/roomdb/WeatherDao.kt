package com.srikanth.synchronoss.roomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.srikanth.synchronoss.model.Weather

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(items: Weather) {

    }

    @Query("SELECT * FROM weatherDB")
    fun getData(): LiveData<Weather>
}