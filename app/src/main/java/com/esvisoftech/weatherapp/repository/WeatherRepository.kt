package com.esvisoftech.weatherapp.repository

import com.esvisoftech.weatherapp.data.DataOrException
import com.esvisoftech.weatherapp.model.City
import com.esvisoftech.weatherapp.model.Weather

import com.esvisoftech.weatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:WeatherApi) {
   suspend fun getWeather(cityQuery: String):DataOrException<Weather,Boolean,Exception>{
        val response=try{
            api.getWeather(cityQuery)
        }catch(e:Exception){
            return DataOrException(e=e)
        }
       return DataOrException(data = response)
    }
}