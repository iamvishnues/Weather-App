package com.esvisoftech.weatherapp.screens.main

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esvisoftech.weatherapp.data.DataOrException
import com.esvisoftech.weatherapp.model.City
import com.esvisoftech.weatherapp.model.Weather
import com.esvisoftech.weatherapp.model.WeatherObject
import com.esvisoftech.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository):ViewModel() {
    val data:MutableState<DataOrException<Weather,Boolean,Exception>>
    = mutableStateOf(DataOrException(null,true,Exception("")))
    init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeather("Seattle")
    }

    private fun getWeather(city: String) {
viewModelScope.launch {
    if(city.isEmpty()) return@launch
    data.value.loading=true
    data.value=repository.getWeather(cityQuery = city)
    if(data.value.data.toString().isEmpty()) {
        data.value.loading=false
    }
    Log.d(TAG, "getWeather: ${data.value.data.toString()}" )
}
    }

}