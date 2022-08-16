package com.esvisoftech.weatherapp.screens.settings

import android.provider.Settings
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.esvisoftech.weatherapp.widgets.WeatherAppBar

@Composable
fun SettingsScreen(navController: NavController,settingsViewModel:
SettingsViewModel= hiltViewModel()){
Scaffold(topBar ={
    WeatherAppBar(title = "Settings", isMainScreen = false, navController = navController)
}) {

}
}