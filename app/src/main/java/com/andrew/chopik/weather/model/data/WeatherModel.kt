package com.andrew.chopik.weather.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Andrew on 14.03.2018.
 */
data class WeatherModel(

        @Transient@SerializedName("coord")  val coord: Coord = Coord(),
        @SerializedName("weather") val weather: List<Weather> = listOf(),
        @SerializedName("base") val base: String = "",
        @SerializedName("main") val main: Main = Main(),
        @SerializedName("visibility") val visibility: Int = 0,
        @SerializedName("wind") val wind: Wind = Wind(),
        @Transient@SerializedName("clouds") val clouds: Clouds = Clouds(),
        @SerializedName("dt") val dt: Long = 0,
        @Transient@SerializedName("sys") val sys: Sys = Sys(),
        @SerializedName("id") val id: Int = 0,
        @SerializedName("name") val name: String = "",
        @SerializedName("cod") val cod: Int = 0
)