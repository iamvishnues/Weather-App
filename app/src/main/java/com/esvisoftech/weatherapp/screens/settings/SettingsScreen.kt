package com.esvisoftech.weatherapp.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.esvisoftech.weatherapp.model.TemperatureUnit
import com.esvisoftech.weatherapp.widgets.WeatherAppBar

@Composable
fun SettingsScreen(navController: NavController,
                   settingsViewModel: SettingsViewModel= hiltViewModel()
){
    var unitToggleState by remember {
        mutableStateOf(false)
    }
    val measurementUnits= listOf("Imperial (F)","Metric (C)")
    val choiceFromDb=settingsViewModel.unitList.collectAsState().value
//    val choiceDef by remember {
//        mutableStateOf(0)
//    }
    val defaultChoice=if(choiceFromDb.isNullOrEmpty()) measurementUnits[0] else choiceFromDb[0].unit
    var choiceState by remember {
        mutableStateOf(defaultChoice)
    }
Scaffold(topBar ={
    WeatherAppBar(title = "Settings", isMainScreen = false, navController = navController,
        icon = Icons.Default.ArrowBack){
        navController.popBackStack()
    }
}) {
androidx.compose.material.Surface(
    modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Text(text = "Change Units of Measurement",
       modifier = Modifier.padding(bottom = 15.dp))

        IconToggleButton(checked =!unitToggleState, onCheckedChange = {
            unitToggleState=!it
            if(unitToggleState){
                choiceState="Imperial (F)"
            }else{
                choiceState="Metric (C)"
            }
        }, modifier = Modifier
            .fillMaxWidth(0.5f)
            .clip(shape = RectangleShape)
            .padding(5.dp)
            .background(Color.Magenta.copy(alpha = 0.4f))) {
            Text(text = if (unitToggleState) "Fahrenheit F" else "Celsius C")
        }
        Button(onClick = {
settingsViewModel.deleteAllUnits()
            settingsViewModel.insertUnit(TemperatureUnit(choiceState))
        },modifier= Modifier
            .padding(3.dp)
            .align(CenterHorizontally)
        ,shape = RoundedCornerShape(20.dp),colors =ButtonDefaults.buttonColors(
                backgroundColor = Color(0xffefbe42)
        )) {
Text(text = "Save", modifier = Modifier.padding(4.dp), color = Color.White, fontSize = 17.sp)
        }
    }
}
}

}