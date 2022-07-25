package com.esvisoftech.weatherapp.screens

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.esvisoftech.weatherapp.data.DataOrException
import com.esvisoftech.weatherapp.model.Weather
import com.esvisoftech.weatherapp.screens.main.MainViewModel
import com.esvisoftech.weatherapp.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController,
               mainViewModel: MainViewModel= hiltViewModel()) {
    val weatherData= produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(
        loading = true),
    ){
        value=mainViewModel.getWeatherData("Seattle")
    }.value
    if (weatherData.loading==true){
        CircularProgressIndicator()
    }else if (weatherData.data!=null){
        MainScaffold(weather=weatherData.data!!,navController=navController)
    }

}

@Composable
fun MainScaffold(weather:Weather,navController: NavController){
Scaffold(topBar = {
    WeatherAppBar(title = weather.city.name+", ${weather.city.country}", navController =navController)
}) {
    MainContent(data= weather)
}

}

@Composable
fun MainContent(data: Weather) {
Text(text = data.city.name)
}
