package com.esvisoftech.weatherapp.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esvisoftech.weatherapp.navigation.WeatherScreens
import com.esvisoftech.weatherapp.widgets.WeatherAppBar

@Composable
fun searchScreen(navController: NavController){
    Scaffold(topBar = { WeatherAppBar(title = "Search", navController = navController, isMainScreen = false,
        icon = Icons.Default.ArrowBack
    ){
        navController.popBackStack()
    }}) {
        androidx.compose.material.Surface() {
            Column(verticalArrangement =Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
SearchBar(modifier =Modifier.fillMaxWidth().padding(16.dp).align(Alignment.CenterHorizontally)){
    mCity->
    navController.navigate(WeatherScreens.MainScreen.name + "/$mCity")
}
            }

            }
        }
    }

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier=Modifier,
    onSearch:(String)->Unit={}){
    val searchQueryState= rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController=LocalSoftwareKeyboardController.current
    val valid=remember(searchQueryState.value){
        searchQueryState.value.trim().isNotEmpty()
    }
    Column {
        CommonTextField(
            valueState=searchQueryState,
            Placeholder="Enter City",
            onAction= KeyboardActions {
                if(!valid) rteurn@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value=""
                keyboardController?.hide()
            }
        )
    }
}

@Composable
fun CommonTextField(valueState: MutableState<String>, Placeholder: String,
               keyboardType:KeyboardType=KeyboardType.Text,
                    imeAction: ImeAction=ImeAction.Next,
                    onAction: KeyboardActions= KeyboardActions.Default) {
OutlinedTextField(value = valueState.value, onValueChange = {
    valueState.value=it
}, label = { Text(text = Placeholder)},
maxLines = 1, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = keyboardType,
    imeAction =imeAction, ), keyboardActions = onAction, colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Blue,
        cursorColor = Color.Black
    ), shape = RoundedCornerShape(10.dp),
modifier = Modifier
    .fillMaxWidth()
    .padding(start = 10.dp, end = 10.dp))
}
