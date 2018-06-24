package com.andrew.chopik.weather.ui.presenter

import android.content.Context
import android.support.v7.widget.SearchView
import com.andrew.chopik.weather.API_KEY
import com.andrew.chopik.weather.R
import com.andrew.chopik.weather.UNITS_METRIC
import com.andrew.chopik.weather.model.WeatherApi
import com.andrew.chopik.weather.model.Response
import com.andrew.chopik.weather.model.data.WeatherModel
import com.andrew.chopik.weather.ui.view.WeatherView
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import retrofit2.HttpException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Andrew on 11.03.2018.
 */
class WeatherPresenterImpl @Inject constructor(val context: Context, val weatherApi: WeatherApi)
    : WeatherPresenter<WeatherView> {

    var view: WeatherView? = null

    val weatherSubject: BehaviorSubject<Response> = BehaviorSubject.create()
    var disposable: Disposable? = null

    override fun onAttach(view: WeatherView) {
        this.view = view

        view.showMessage(context.getString(R.string.start_message))
        subscribeForUpdates()
    }

    override fun onReadySearch(searchView: SearchView) {
        disposable = RxSearchView.queryTextChanges(searchView)
                .observeOn(Schedulers.io())
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter { it.isNotEmpty() }
                .map(CharSequence::toString)
                .flatMap { city ->
                    weatherApi.getWeather(city, API_KEY, UNITS_METRIC)
                            .map { w -> Response(w) }
                            .onErrorReturn { t -> Response(WeatherModel(), t) }
                }
                .subscribe(
                        { result -> weatherSubject.onNext(result) }
                )
    }

    override fun onDetach() {
        this.view = null
        disposable?.dispose()
    }

    fun subscribeForUpdates() {
        weatherSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> checkResponse(result) }
                )
    }

    fun checkResponse(response: Response) {
        when (response.error) {
            null -> updateWeather(response.weatherModel)
            else -> checkError(response.error)
        }
    }

    fun checkError(error: Throwable) {
        val errorMessage: String
        when (error) {
            is UnknownHostException -> errorMessage = context.getString(R.string.error_network)
            is HttpException -> errorMessage = context.getString(R.string.error_city_name)
            else -> errorMessage = context.getString(R.string.error)
        }

        view?.showMessage(errorMessage)
    }

    fun updateWeather(weather: WeatherModel) {
        weather.weather[0].id = getImageResourceID(weather.weather[0].icon)
        view?.showWeather(weather)
    }

    fun getImageResourceID(weather: String) =
            when (weather) {
                "01d" -> R.drawable.ic_sun
                "01n" -> R.drawable.ic_moon
                "02d" -> R.drawable.ic_cloudy
                "02n" -> R.drawable.ic_cloud_night
                "03d", "03n", "04d", "04n" -> R.drawable.ic_clouds
                "09d", "10d" -> R.drawable.ic_rain
                "09n", "10n" -> R.drawable.ic_night_rain
                "11d", "11n" -> R.drawable.ic_storm
                "13d", "13n" -> R.drawable.ic_snow
                "50d", "50n" -> R.drawable.ic_fog
                else -> R.drawable.ic_not_found
            }
}