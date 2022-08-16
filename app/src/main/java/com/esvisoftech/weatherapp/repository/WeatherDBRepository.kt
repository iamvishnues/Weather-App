package com.esvisoftech.weatherapp.repository

import com.esvisoftech.weatherapp.data.WeatherDao
import com.esvisoftech.weatherapp.model.City
import com.esvisoftech.weatherapp.model.Favorite
import com.esvisoftech.weatherapp.model.Unit
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class WeatherDBRepository @Inject constructor(private val weatherDao: WeatherDao) {
    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite)=weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite)=weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites()=weatherDao.deleteAllFavorites()
    suspend fun deleteFavorites(favorite: Favorite)=weatherDao.deleteFavorite(favorite)
    suspend fun getFavById(city: String):Favorite=weatherDao.getFavById(city)

    fun getUnits():Flow<List<Unit>> = weatherDao.getUnits()
    suspend fun  insertUnit(unit:Unit)=weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit: Unit)=weatherDao.updateUnit(unit)
}