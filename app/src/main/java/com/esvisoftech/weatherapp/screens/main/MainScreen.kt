package com.esvisoftech.weatherapp.screens

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.esvisoftech.weatherapp.data.DataOrException
import com.esvisoftech.weatherapp.model.Weather
import com.esvisoftech.weatherapp.screens.main.MainViewModel
import kotlin.math.log

@Composable
fun MainScreen(navController: NavController,
               mainViewModel: MainViewModel= hiltViewModel()) {
showData(mainViewModel)
}

@Composable
fun showData(mainViewModel: MainViewModel){
    val weatherData= produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(
        loading = true),
         ){
value=mainViewModel.getWeatherData("Seattle")
    }.value
    if (weatherData.loading==true){
        CircularProgressIndicator()
    }else if (weatherData.data!=null){
        Text("MainScreen ${weatherData.data!!.city.country}")
    }

}