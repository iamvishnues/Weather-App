package com.esvisoftech.weatherapp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.esvisoftech.weatherapp.R
import com.esvisoftech.weatherapp.data.DataOrException
import com.esvisoftech.weatherapp.model.Weather
import com.esvisoftech.weatherapp.model.WeatherItem
import com.esvisoftech.weatherapp.navigation.WeatherScreens
import com.esvisoftech.weatherapp.screens.main.MainViewModel
import com.esvisoftech.weatherapp.utils.formatDate
import com.esvisoftech.weatherapp.utils.formatDateTime
import com.esvisoftech.weatherapp.utils.formatDecimals
import com.esvisoftech.weatherapp.widgets.*

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
) {
    val weatherData= produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(
        loading = true),
    ){
        value=mainViewModel.getWeatherData(city=city.toString())
    }.value
    if (weatherData.loading==true){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }else if (weatherData.data!=null){
        MainScaffold(weather=weatherData.data!!,navController=navController)
    }

}

@Composable
fun MainScaffold(weather:Weather,navController: NavController){
Scaffold(topBar = {
    WeatherAppBar(title = weather.city.name+", ${weather.city.country}", navController =navController
    , onAddActionClicked = {
        navController.navigate(WeatherScreens.SearchScreen.name)
        } ){
        Log.d("TAG","Button clicked")
    }
}) {
    MainContent(data= weather)
}

}

@Composable
fun MainContent(data: Weather) {
    val imageUrl="https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    val weatherItems=data.list[0]
Column(modifier = Modifier
    .padding(4.dp)
    .fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
Text(text = formatDate(data.list[0].dt), style = MaterialTheme.typography.caption,
color=MaterialTheme.colors.onSecondary,
fontWeight = FontWeight.SemiBold,
modifier = Modifier.padding(6.dp), fontSize = 15.sp

)
    Surface (modifier = Modifier
        .padding(4.dp)
        .size(200.dp),
    shape = CircleShape, color = Color(0xFFFFC680)
    ){
Column(verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally){
    WeatherStateImage(imageUrl)
    Text(text = formatDecimals(data.list[0].main.temp)+"°", style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold)
    Text(text = data.list[0].weather[0].description, fontStyle = FontStyle.Italic)
}
    }

HumidityWindPressureRow(weather =weatherItems)
Divider(thickness = 2.dp)
SunriseAndSunset(weather=data)
    Divider()
WeatherForecast(historyData=data)
}
}
