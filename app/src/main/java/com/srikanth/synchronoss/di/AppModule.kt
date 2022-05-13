package com.srikanth.synchronoss.di

import android.app.Application
import com.srikanth.synchronoss.retrofit.RetrofitService
import com.srikanth.synchronoss.roomdb.WeatherDB
import com.srikanth.synchronoss.roomdb.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun getDatabase(context: Application): WeatherDB {
        return WeatherDB.getDatabase(context)
    }

    @Provides
    @Singleton
    fun getAppDao(weatherDB: WeatherDB): WeatherDao {
        return weatherDB.weatherDao()
    }

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): RetrofitService =
        retrofit.create(RetrofitService::class.java)
}

/*
@Provides
@Singleton
fun provideWorkManager(
    @ApplicationContext context: Context
) = WeatherManager.getInstance(context)*/
