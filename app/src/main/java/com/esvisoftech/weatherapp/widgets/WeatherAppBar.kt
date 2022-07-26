package com.esvisoftech.weatherapp.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.esvisoftech.weatherapp.model.Favorite
import com.esvisoftech.weatherapp.navigation.WeatherScreens
import com.esvisoftech.weatherapp.screens.favourites.FavoriteViewModel

@Composable
fun WeatherAppBar(
    title: String,
    icon:ImageVector?=null,
    isMainScreen:Boolean=true,
    elevation: Dp=5.dp,
    favoriteViewModel: FavoriteViewModel= hiltViewModel(),
    navController:NavController,
    onAddActionClicked:()->Unit={},
    onButtonClicked:()->Unit={}
) {
    val showDialog=remember{
  mutableStateOf(false)
    }

    val showIt = remember {
        mutableStateOf(false)
    }
    val context= LocalContext.current

    if(showDialog.value){
 ShowSettingDropDown(showDialog=showDialog,navController=navController)
    }

    TopAppBar(title={
                    Text(text = title)
    },
        actions = {
                  if (isMainScreen){
                      IconButton(onClick = {
                          onAddActionClicked.invoke()
                      }) {
                          Icon(
                              imageVector = Icons.Default.Search,
                              contentDescription = "Search Icon"
                          )
                      }
                      IconButton(onClick = {
showDialog.value=true
                      }) {
                          Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Options")
                      }

                  }
            else{
                Box{}
                  }
        },
        navigationIcon = {
                       if(icon!=null){
                           Icon(imageVector = icon, contentDescription = null,
                           tint = MaterialTheme.colors.onSecondary,
                           modifier = Modifier.clickable {
                               onButtonClicked.invoke()
                           })
                       }

            if(isMainScreen){
                val isAlreadyfavList=favoriteViewModel.favList.collectAsState().value.filter {
                    item->(item.city==title.split(",")[0])
                }
                val dataList=title.split(",")
                if(isAlreadyfavList.isNullOrEmpty()){
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite Icons", tint = Color.Red.copy(alpha = 0.6f)
                        ,modifier= Modifier
                            .scale(0.9f)
                            .clickable {
                                favoriteViewModel.insertFavorite(
                                    Favorite(
                                        city = dataList[0],
                                        country = dataList[1]
                                    )
                                ).run {
                                    showIt.value=true
                                }
                            }
                    )
                }
               else{
                   showIt.value=false
                   Box{}
                }
                ShowToast(context = context,showIt)
            }

        },
    backgroundColor = Color.Transparent, elevation = elevation)
}


@Composable
fun ShowToast(context: Context, showIt: MutableState<Boolean>) {
if (showIt.value){
    Toast.makeText(context,"Added to Favorites",Toast.LENGTH_SHORT)
}
}

@Composable
fun ShowSettingDropDown(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val items= listOf("About","Favorites","Settings")
Column(modifier = Modifier
    .fillMaxWidth()
    .wrapContentSize(Alignment.TopEnd)
    .absolutePadding(
        top = 45.dp, right = 20.dp
    )) {
    DropdownMenu(expanded =expanded
        , onDismissRequest = {
            expanded=false
        }, modifier = Modifier
            .width(140.dp)
            .background(Color.White)) {
items.forEachIndexed { index, text ->
    DropdownMenuItem(onClick = {
        expanded=false
        showDialog.value=false
    }) {
        Icon(imageVector =when(text){
                                    "About"->Icons.Default.Info
            "Favorites"->Icons.Default.Favorite
            "Settings"->Icons.Default.Settings
            else->Icons.Default.Refresh
                                    } , contentDescription = ""
        , tint = Color.LightGray, )
        Text(text = text, modifier = Modifier.clickable {
navController.navigate(
    when(text){
        "About"->WeatherScreens.AboutScreen.name
            "Favorites"->WeatherScreens.FavoriteScreen.name
        "Settings"->WeatherScreens.SettingsScreen.name
        else->WeatherScreens.MainScreen.name
    }
)
        }, fontWeight = FontWeight.W300)
    }
}
    }
}
}
