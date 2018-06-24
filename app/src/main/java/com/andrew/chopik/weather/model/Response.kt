package com.andrew.chopik.weather.model

import com.andrew.chopik.weather.model.data.WeatherModel

/**
 * Created by Andrew on 17.03.2018.
 */
data class Response(val weatherModel: WeatherModel, val error: Throwable? = null)