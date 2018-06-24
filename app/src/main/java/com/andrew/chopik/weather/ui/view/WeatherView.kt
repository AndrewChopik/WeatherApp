package com.andrew.chopik.weather.ui.view

import com.andrew.chopik.weather.model.data.WeatherModel

/**
 * Created by Andrew on 11.03.2018.
 */
interface WeatherView {

    fun showWeather(weather: WeatherModel)

    fun showMessage(message: String)
}