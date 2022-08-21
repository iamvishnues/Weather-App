package com.esvisoftech.weatherapp.data

import androidx.room.*
import com.esvisoftech.weatherapp.model.Favorite
import com.esvisoftech.weatherapp.model.TemperatureUnit
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {
    @Query("SELECT * FROM fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM fav_tbl WHERE city=:city")
    suspend fun getFavById(city:String):Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("DELETE FROM fav_tbl")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM settings_tbl")
    fun getUnits():Flow<List<TemperatureUnit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(temperatureUnit: TemperatureUnit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(temperatureUnit: TemperatureUnit)

    @Query("DELETE FROM settings_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(temperatureUnit: TemperatureUnit)

}