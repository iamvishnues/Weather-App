package com.esvisoftech.weatherapp.repository

import android.content.ContentValues
import android.nfc.Tag
import android.util.Log
import com.esvisoftech.weatherapp.data.DataOrException
import com.esvisoftech.weatherapp.model.City
import com.esvisoftech.weatherapp.model.Weather

import com.esvisoftech.weatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:WeatherApi) {
   suspend fun getWeather(cityQuery: String, units: String):DataOrException<Weather,Boolean,Exception>{
        val response=try{
            api.getWeather(cityQuery, units = units)
        }catch(e:Exception){
            Log.d("Excpet", "getWeather: ${e}" )
            return DataOrException(e=e)
        }
//       Log.d("Inside", "getWeather: ${response}" )
       return DataOrException(data = response)
    }
}