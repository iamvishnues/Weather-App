package com.esvisoftech.weatherapp.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.esvisoftech.weatherapp.R
import com.esvisoftech.weatherapp.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController){
Scaffold(topBar = { WeatherAppBar(title = "About", navController = navController,
icon = Icons.Default.ArrowBack, isMainScreen = false,){
    navController.popBackStack()
}}) {
    androidx.compose.material.Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.about_app), style =MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
            )
            Text(text = stringResource(id = R.string.app_api))
        }
    }
}
}