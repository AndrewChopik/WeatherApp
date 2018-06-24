package com.andrew.chopik.weather.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Andrew on 14.03.2018.
 */
data class Wind(
        @SerializedName("speed") val speed: Double = 0.0,
        @SerializedName("deg") val deg: Double = 0.0
) : Serializable