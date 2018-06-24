package com.andrew.chopik.weather.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Andrew on 14.03.2018.
 */
data class Weather(
        @SerializedName("id") var id: Int = 0,
        @SerializedName("main") val main: String = "",
        @SerializedName("description") val description: String = "",
        @SerializedName("icon") val icon: String = ""
)