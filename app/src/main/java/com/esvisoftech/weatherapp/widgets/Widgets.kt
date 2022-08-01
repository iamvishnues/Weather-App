package com.esvisoftech.weatherapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.esvisoftech.weatherapp.R
import com.esvisoftech.weatherapp.model.Weather
import com.esvisoftech.weatherapp.model.WeatherItem
import com.esvisoftech.weatherapp.utils.formatDate
import com.esvisoftech.weatherapp.utils.formatDateTime
import com.esvisoftech.weatherapp.utils.formatDecimals


@Composable
fun WeatherForecast(historyData: Weather) {
    Text("Weather Forecast", style = MaterialTheme.typography.h6, modifier =
    Modifier
        .padding(bottom = 20.dp)
        .padding(top = 10.dp))
    Column() {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color= Color(0xFFEEF1EF),
            shape = RoundedCornerShape(14.dp)
        ) {
            LazyColumn(modifier = Modifier.padding(),
                contentPadding = PaddingValues(2.dp)
            ){
                items(items=historyData.list){
                        item: WeatherItem ->
                    val imageUrl="https://openweathermap.org/img/wn/${item.weather[0].icon}.png"
                    Card(modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                        .height(85.dp)) {
                        Row(modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Column() {
                                Text(formatDate(item.dt).split(",")[0], fontWeight = FontWeight.Bold)
                                Text(text = formatDateTime(item.dt))
                            }

                            WeatherStateImage(imageUrl = imageUrl)
                            Surface(modifier = Modifier.padding(2.dp),
                                shape = RoundedCornerShape(5.dp),
                                color = Color(0xffffc400)
                            ) {
                                Text(text = "${item.weather[0].description}", modifier = Modifier.padding(5.dp))
                            }
                            Column {

                                Text(text = "max: ${formatDecimals(item.main.temp_max)}°")
                                Text(text = "min: ${formatDecimals(item.main.temp_min)}°")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SunriseAndSunset(weather: Weather) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.sun), contentDescription ="sunrise",
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp) )
            Text(text = formatDateTime(weather.city.sunrise))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.sunset), contentDescription ="sunset",
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp) )
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
            Text(text = "${weather.wind.speed} mph", style = MaterialTheme.typography.subtitle2)
        }
    }
}


@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(painter = rememberAsyncImagePainter(imageUrl), contentDescription ="Icon Image"
        ,modifier = Modifier.size(70.dp), contentScale = ContentScale.Fit)
}
