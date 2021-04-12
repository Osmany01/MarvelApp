package com.marvelapp

import android.app.Application
import com.marvelapp.di.DaggerMarvelAppComponent
import com.marvelapp.di.MarvelAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MarvelApplication: Application(), HasAndroidInjector {


    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var marvelAppComponent: MarvelAppComponent

    override fun onCreate() {
        super.onCreate()

        marvelAppComponent = DaggerMarvelAppComponent.builder()
            .application(this)
            .build()
        marvelAppComponent.inject(this)
    }


    override fun androidInjector(): AndroidInjector<Any> {

        return androidInjector
    }
}