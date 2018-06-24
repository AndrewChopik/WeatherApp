package com.andrew.chopik.weather.ui.presenter

import android.support.v7.widget.SearchView


/**
 * Created by Andrew on 14.03.2018.
 */
interface WeatherPresenter<in WeatherView> {

    fun onAttach(view: WeatherView)

    fun onDetach()

    fun onReadySearch(searchView: SearchView)
}