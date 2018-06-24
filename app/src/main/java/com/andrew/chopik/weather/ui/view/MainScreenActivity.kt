package com.andrew.chopik.weather.ui.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import com.andrew.chopik.weather.R
import com.andrew.chopik.weather.model.data.WeatherModel
import com.andrew.chopik.weather.ui.App
import com.andrew.chopik.weather.ui.presenter.WeatherPresenterImpl
import kotlinx.android.synthetic.main.activity_main_screen.*
import javax.inject.Inject

class MainScreenActivity : AppCompatActivity(), WeatherView {

    @Inject
    lateinit var presenter: WeatherPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        App.activityComponent.inject(this)
        presenter.onAttach(this)

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchView = menu.findItem(R.id.find_city).actionView as SearchView
        presenter.onReadySearch(searchView)

        return true
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDetach()
    }

    override fun showWeather(weather: WeatherModel) {
        switchToWeather()

        descriptionTextView.text = weather.weather[0].description
        tempTextView.text = getString(R.string.current_temp, weather.main.temp)
        pressureTextView.text = getString(R.string.current_pressure, weather.main.pressure)
        humidityTextView.text = getString(R.string.current_humidity, weather.main.humidity)
        windTextView.text = getString(R.string.current_wind, weather.wind.speed)
        supportActionBar?.title = weather.name
        weatherImageView.setImageResource(weather.weather[0].id)
    }

    override fun showMessage(message: String) {
        switchToMessage()

        messageTextView.text = message
    }

    fun switchToWeather() {
        messageTextView.visibility = View.GONE
        frame.visibility = View.VISIBLE
    }

    fun switchToMessage() {
        frame.visibility = View.GONE
        messageTextView.visibility = View.VISIBLE
    }
}
