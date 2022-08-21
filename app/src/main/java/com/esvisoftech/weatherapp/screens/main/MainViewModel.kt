package com.esvisoftech.weatherapp.screens.main

import androidx.lifecycle.ViewModel
import com.esvisoftech.weatherapp.data.DataOrException
import com.esvisoftech.weatherapp.model.Weather
import com.esvisoftech.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository):ViewModel() {

    suspend fun getWeatherData(city: String, units: String):DataOrException<Weather,Boolean,Exception>{
        return repository.getWeather(cityQuery = city,units=units)
    }

}