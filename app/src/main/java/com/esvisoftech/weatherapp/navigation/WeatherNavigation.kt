package com.esvisoftech.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.esvisoftech.weatherapp.screens.MainScreen
import com.esvisoftech.weatherapp.screens.WeatherSplashScreen
import com.esvisoftech.weatherapp.screens.about.AboutScreen
import com.esvisoftech.weatherapp.screens.favourites.FavoriteScreen
import com.esvisoftech.weatherapp.screens.main.MainViewModel
import com.esvisoftech.weatherapp.screens.search.searchScreen
import com.esvisoftech.weatherapp.screens.settings.SettingsScreen

@Composable
fun WeatherNavigation() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController=navController)
        }
        val route=WeatherScreens.MainScreen.name
        composable("$route/{city}",
        arguments = listOf(
            navArgument(name = "city"){
                type= NavType.StringType
            }
        )){
            navBack->navBack.arguments?.getString("city").let {
           city-> val mainViewModel= hiltViewModel<MainViewModel>()
            MainScreen(navController=navController,mainViewModel,city=city)
        }

        }
        composable(WeatherScreens.SearchScreen.name){
            searchScreen(navController = navController)
        }
        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController=navController)
        }
        composable(WeatherScreens.SettingsScreen.name){
            SettingsScreen(navController =navController )
        }
        composable(WeatherScreens.FavoriteScreen.name){
            FavoriteScreen(navController = navController)
        }
    }
}


