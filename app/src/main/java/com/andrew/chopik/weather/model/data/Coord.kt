package com.andrew.chopik.weather.model.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 14.03.2018.
 */
data class Coord(
        @SerializedName("lon") val lon: Double = 0.0,
        @SerializedName("lat") val lat: Double = 0.0
)