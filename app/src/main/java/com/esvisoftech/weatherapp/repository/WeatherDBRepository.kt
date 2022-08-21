package com.esvisoftech.weatherapp.repository

import com.esvisoftech.weatherapp.data.WeatherDao
import com.esvisoftech.weatherapp.model.City
import com.esvisoftech.weatherapp.model.Favorite
import com.esvisoftech.weatherapp.model.TemperatureUnit
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class WeatherDBRepository @Inject constructor(private val weatherDao: WeatherDao) {
    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite)=weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite)=weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites()=weatherDao.deleteAllFavorites()
    suspend fun deleteFavorites(favorite: Favorite)=weatherDao.deleteFavorite(favorite)
    suspend fun getFavById(city: String):Favorite=weatherDao.getFavById(city)

    fun getUnits():Flow<List<TemperatureUnit>> = weatherDao.getUnits()
    suspend fun  insertUnit(temperatureUnit: TemperatureUnit)=weatherDao.insertUnit(temperatureUnit)
    suspend fun updateUnit(temperatureUnit: TemperatureUnit)=weatherDao.updateUnit(temperatureUnit)
    suspend fun deleteAllUnits()=weatherDao.deleteAllUnits()
    suspend fun deleteUnit(temperatureUnit: TemperatureUnit)=weatherDao.deleteUnit(temperatureUnit)
}