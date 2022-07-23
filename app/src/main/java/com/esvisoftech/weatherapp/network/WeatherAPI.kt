package com.esvisoftech.weatherapp.network

import com.esvisoftech.weatherapp.model.Weather
import com.esvisoftech.weatherapp.model.WeatherObject
import com.esvisoftech.weatherapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value ="data/2.5/forecast")
    suspend fun getWeather(@Query("q") query:String,
    @Query("units") units: String="imperial",
    @Query("appid") appid:String= Constants.API_KEY
    ):Weather
}