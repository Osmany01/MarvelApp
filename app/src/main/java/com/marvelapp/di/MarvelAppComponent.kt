package com.marvelapp.di

import android.app.Application
import com.example.data.di.DataModule
import com.marvelapp.ui.characters.CharactersActivity
import com.marvelapp.MarvelApplication
import com.marvelapp.ui.characters.CharactersActivityModule
import com.marvelapp.ui.characters.CharactersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ViewModelModule::class,
    CharactersModule::class,
    CharactersActivityModule::class,
    DataModule::class
])
interface MarvelAppComponent {

    fun inject(charactersActivity: CharactersActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): MarvelAppComponent
    }

    fun inject(app: MarvelApplication)
}