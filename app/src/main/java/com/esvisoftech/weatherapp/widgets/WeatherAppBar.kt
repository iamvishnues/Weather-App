package com.esvisoftech.weatherapp.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WeatherAppBar(
    title: String,
    icon:ImageVector?=null,
    isMainScreen:Boolean=true,
    elevation: Dp=5.dp,
    navController:NavController,
    onAddActionClicked:()->Unit={},
    onButtonClicked:()->Unit={}
) {
    TopAppBar(title={
                    Text(text = title)
    },
        actions = {
                  if (isMainScreen){
                      IconButton(onClick = { /*TODO*/ }) {
                          Icon(
                              imageVector = Icons.Default.Search,
                              contentDescription = "Search Icon"
                          )
                      }
                      IconButton(onClick = { /*TODO*/ }) {
                          Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Options")
                      }
                  }
            else{
                Box{}
                  }
        },
        navigationIcon = {},
    backgroundColor = Color.Transparent, elevation = elevation)
}