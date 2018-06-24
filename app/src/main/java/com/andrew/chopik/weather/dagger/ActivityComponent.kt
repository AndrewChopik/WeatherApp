package com.andrew.chopik.weather.dagger

import android.content.Context
import com.andrew.chopik.weather.ui.view.MainScreenActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Andrew on 15.03.2018.
 */
@Singleton
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainScreenActivity: MainScreenActivity)

    @Component.Builder
    interface Builder {

        fun build(): ActivityComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}