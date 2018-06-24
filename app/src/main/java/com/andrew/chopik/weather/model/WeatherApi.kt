package com.andrew.chopik.weather.model

import com.andrew.chopik.weather.model.data.WeatherModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Andrew on 10.03.2018.
 */
interface WeatherApi {

    @GET("/data/2.5/weather")
    fun getWeather(@Query("q") cityName: String,
                   @Query("APPID") key: String,
                   @Query("units") units: String): Observable<WeatherModel>
}