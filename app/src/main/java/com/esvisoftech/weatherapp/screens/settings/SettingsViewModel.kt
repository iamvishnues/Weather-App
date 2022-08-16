package com.esvisoftech.weatherapp.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esvisoftech.weatherapp.model.Unit
import com.esvisoftech.weatherapp.repository.WeatherDBRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

class SettingsViewModel @Inject constructor(private val repository:WeatherDBRepository):ViewModel() {
    private val _unitList=MutableStateFlow<List<Unit>>(emptyList())
    val unitList=_unitList
    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getUnits().distinctUntilChanged()
                .collect{
                    listOfunits->if(listOfunits.isNullOrEmpty()){
                        Log.d("TAG","EMPTY LIST")
                }
                }
        }
    }
    fun insertUnit(unit: Unit)=viewModelScope.launch{
        repository.insertUnit(unit)
    }
    fun updateUnit(unit: Unit)=viewModelScope.launch {
        repository.updateUnit(unit)
    }
}