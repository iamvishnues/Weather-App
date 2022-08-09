package com.esvisoftech.weatherapp.screens.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esvisoftech.weatherapp.model.Favorite
import com.esvisoftech.weatherapp.repository.WeatherDBRepository
import com.esvisoftech.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDBRepository):ViewModel() {
    private val _favList=MutableStateFlow<List<Favorite>>(emptyList())
    val favList=_favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getFavorites().distinctUntilChanged().collect{
                listOfFavs->if (listOfFavs.isNullOrEmpty()){
                   print("Empty List")
            }else{
                _favList.value=listOfFavs
                print(favList.value+"<>>>>>>>>>>>>>>>>>>>>>>>>")
            }
            }
        }
    }
    fun insertFavorite(favorite: Favorite)=viewModelScope.launch {
        repository.insertFavorite(favorite)
    }

    fun updateFavorite(favorite: Favorite)=viewModelScope.launch {
        repository.updateFavorite(favorite)
    }

    fun deleteFavorite(favorite: Favorite)=viewModelScope.launch {
        repository.deleteFavorites(favorite)
    }

    fun getFavById(city:String)=viewModelScope.launch {
        repository.getFavById(city)
    }
}