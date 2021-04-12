package com.marvelapp.di

import android.app.Application
import com.example.data.di.DataModule
import com.marvelapp.MarvelApplication
import com.marvelapp.ui.characterdetails.di.CharacterDetailsActivityModule
import com.marvelapp.ui.characterdetails.di.CharacterDetailsModule
import com.marvelapp.ui.characters.di.CharactersActivityModule
import com.marvelapp.ui.characters.di.CharactersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ViewModelModule::class,
    CharactersModule::class,
    CharactersActivityModule::class,
    CharacterDetailsActivityModule::class,
    CharacterDetailsModule::class,
    DataModule::class
])
interface MarvelAppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): MarvelAppComponent
    }

    fun inject(app: MarvelApplication)
}