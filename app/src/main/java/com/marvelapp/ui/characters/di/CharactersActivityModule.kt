package com.marvelapp.ui.characters.di

import com.marvelapp.ui.characters.CharactersActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
abstract class CharactersActivityModule {

    @ContributesAndroidInjector
    abstract fun providesMainActivity(): CharactersActivity
}