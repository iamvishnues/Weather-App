package com.esvisoftech.weatherapp.screens.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esvisoftech.weatherapp.data.DataOrException
import com.esvisoftech.weatherapp.model.Weather
import com.esvisoftech.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository):ViewModel() {

    suspend fun getWeatherData(city:String):DataOrException<Weather,Boolean,Exception>{
        return repository.getWeather(cityQuery = city)
    }

}