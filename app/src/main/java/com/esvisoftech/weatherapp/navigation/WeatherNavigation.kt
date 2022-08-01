package com.esvisoftech.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esvisoftech.weatherapp.screens.MainScreen
import com.esvisoftech.weatherapp.screens.WeatherSplashScreen
import com.esvisoftech.weatherapp.screens.main.MainViewModel
import com.esvisoftech.weatherapp.screens.search.searchScreen

@Composable
fun WeatherNavigation() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController=navController)
        }
        composable(WeatherScreens.MainScreen.name){
            val mainViewModel= hiltViewModel<MainViewModel>()
            MainScreen(navController=navController,mainViewModel)
        }
        composable(WeatherScreens.SearchScreen.name){
            searchScreen(navController = navController)
        }
    }
}


