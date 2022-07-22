package com.esvisoftech.weatherapp.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esvisoftech.weatherapp.R
import com.esvisoftech.weatherapp.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController:NavController) {
    val scale=remember{
        androidx.compose.animation.core.Animatable(0f)
    }

    //Animation
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.9f, animationSpec = tween(800,
        easing = {
            OvershootInterpolator(8f).getInterpolation(it)
        }))
        delay(2000L)
        navController.navigate(WeatherScreens.MainScreen.name)
    })

    androidx.compose.material.Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)
        .scale(scale.value)
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