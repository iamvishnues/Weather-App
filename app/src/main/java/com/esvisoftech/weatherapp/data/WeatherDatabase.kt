package com.esvisoftech.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esvisoftech.weatherapp.model.Favorite
import com.esvisoftech.weatherapp.model.TemperatureUnit

@Database(entities = [Favorite::class,TemperatureUnit::class], version = 4, exportSchema = false)
abstract class WeatherDatabase:RoomDatabase() {
    abstract fun weatherDao():WeatherDao
}