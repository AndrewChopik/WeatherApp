package com.andrew.chopik.weather.dagger

import android.content.Context
import com.andrew.chopik.weather.BASE_URL
import com.andrew.chopik.weather.model.WeatherApi
import com.andrew.chopik.weather.ui.presenter.WeatherPresenterImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Andrew on 15.03.2018.
 */
@Module
class ActivityModule {

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)

    @Singleton
    @Provides
    fun providePresenter(context: Context, weatherApi: WeatherApi) = WeatherPresenterImpl(context, weatherApi)
}