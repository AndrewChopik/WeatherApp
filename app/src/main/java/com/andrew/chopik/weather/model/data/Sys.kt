package com.andrew.chopik.weather.model.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 14.03.2018.
 */
data class Sys(
        @SerializedName("type") val type: Int = 0,
        @SerializedName("id") val id: Int = 0,
        @SerializedName("message") val message: Double = 0.0,
        @SerializedName("country") val country: String = "",
        @SerializedName("sunrise") val sunrise: Int = 0,
        @SerializedName("sunset") val sunset: Int = 0
)