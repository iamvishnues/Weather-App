package com.esvisoftech.weatherapp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.esvisoftech.weatherapp.R
import com.esvisoftech.weatherapp.data.DataOrException
import com.esvisoftech.weatherapp.model.Weather
import com.esvisoftech.weatherapp.model.WeatherItem
import com.esvisoftech.weatherapp.screens.main.MainViewModel
import com.esvisoftech.weatherapp.utils.formatDate
import com.esvisoftech.weatherapp.utils.formatDateTime
import com.esvisoftech.weatherapp.utils.formatDecimals
import com.esvisoftech.weatherapp.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController,
               mainViewModel: MainViewModel= hiltViewModel()) {
    val weatherData= produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(
        loading = true),
    ){
        value=mainViewModel.getWeatherData("Bangalore")
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
    WeatherAppBar(title = weather.city.name+", ${weather.city.country}", navController =navController
    , ){
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
}
}

@Composable
fun SunriseAndSunset(weather:Weather) {
Row(modifier = Modifier.padding(12.dp).fillMaxWidth(), horizontalArrangement =Arrangement.SpaceBetween) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.sun), contentDescription ="sunrise",
            modifier = Modifier.size(40.dp).padding(4.dp) )
        Text(text = formatDateTime(weather.city.sunrise))
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.sunset), contentDescription ="sunset",
            modifier = Modifier.size(40.dp).padding(4.dp) )
        Text(text = formatDateTime(weather.city.sunset))
    }

}
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
Row(modifier = Modifier
    .padding(12.dp)
    .fillMaxWidth(),
horizontalArrangement = Arrangement.SpaceBetween) {
    Row(modifier = Modifier.padding(4.dp),verticalAlignment = Alignment.CenterVertically){
       Icon(painter = painterResource(id = R.drawable.humidity), contentDescription ="humidity",
        modifier = Modifier
            .size(25.dp)
            .padding(4.dp))
        Text(text = "${weather.main.humidity}", style = MaterialTheme.typography.caption)
    }
    Row(modifier = Modifier.padding(4.dp),verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = R.drawable.indicator), contentDescription ="pressure",
            modifier = Modifier
                .size(25.dp)
                .padding(4.dp))
        Text(text = "${weather.main.pressure} psi", style = MaterialTheme.typography.caption)
    }
    Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically){
        Icon(painter = painterResource(id = R.drawable.storm), contentDescription ="wind",
            modifier = Modifier
                .size(25.dp)
                .padding(4.dp))
        Text(text = "${weather.main.humidity} mph", style = MaterialTheme.typography.subtitle2)
    }
}
}


@Composable
fun WeatherStateImage(imageUrl: String) {
Image(painter = rememberAsyncImagePainter(imageUrl), contentDescription ="Icon Image"
,modifier =Modifier.size(70.dp), contentScale = ContentScale.FillBounds)
}
