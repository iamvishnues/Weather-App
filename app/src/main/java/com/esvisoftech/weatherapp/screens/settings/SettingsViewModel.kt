package com.esvisoftech.weatherapp.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esvisoftech.weatherapp.model.TemperatureUnit
import com.esvisoftech.weatherapp.repository.WeatherDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: WeatherDBRepository):ViewModel() {
    private val _unitList=MutableStateFlow<List<TemperatureUnit>>(emptyList())
    val unitList=_unitList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getUnits().distinctUntilChanged().collect{
                    listOfunits->
                    if(listOfunits.isNullOrEmpty()){
                        Log.d("TAG","EMPTY LIST")
                }else{
                    _unitList.value=listOfunits
                }
                }
        }
    }
    fun insertUnit(temperatureUnit: TemperatureUnit)=viewModelScope.launch{
        repository.insertUnit(temperatureUnit)
    }
    fun updateUnit(temperatureUnit: TemperatureUnit)=viewModelScope.launch {
        repository.updateUnit(temperatureUnit)
    }
    fun deleteUnit(temperatureUnit: TemperatureUnit)=viewModelScope.launch {
        repository.deleteUnit(temperatureUnit)
    }
    fun deleteAllUnits()=viewModelScope.launch {
        repository.deleteAllUnits()
    }
}