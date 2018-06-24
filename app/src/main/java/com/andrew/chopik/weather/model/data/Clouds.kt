package com.andrew.chopik.weather.model.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 14.03.2018.
 */
data class Clouds(
        @SerializedName("all") val all: Int = 0
)