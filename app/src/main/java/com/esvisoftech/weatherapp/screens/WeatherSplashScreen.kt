package com.esvisoftech.weatherapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esvisoftech.weatherapp.R

@Composable
fun WeatherSplashScreen(navController:NavController) {
    androidx.compose.material.Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)
//        , shape= CircleShape,color= Color.White,
//    border = BorderStroke(width = 2.dp,color=Color.LightGray
//    )
    ) {
Column(modifier = Modifier.padding(1.dp),
horizontalAlignment = Alignment.CenterHorizontally,
verticalArrangement = Arrangement.Center) {
Image(painter = painterResource(id = R.drawable.splash_icon
), contentScale = ContentScale.Fit, contentDescription ="Weatherapp Splash Screen Icon", modifier =
Modifier.size(145.dp) )
    Box(modifier = Modifier.height(10.dp))
    Text(text = "Weather", style = MaterialTheme.typography.h4)
}
    }
}