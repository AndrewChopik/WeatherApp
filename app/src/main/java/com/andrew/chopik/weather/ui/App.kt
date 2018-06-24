package com.andrew.chopik.weather.ui

import android.app.Application
import com.andrew.chopik.weather.dagger.ActivityComponent
import com.andrew.chopik.weather.dagger.DaggerActivityComponent

/**
 * Created by Andrew on 16.03.2018.
 */
class App : Application() {

    companion object {
        lateinit var activityComponent: ActivityComponent
    }

    override fun onCreate() {
        super.onCreate()

        activityComponent = DaggerActivityComponent.builder()
                .context(this)
                .build()
    }
}