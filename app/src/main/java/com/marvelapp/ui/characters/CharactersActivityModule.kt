package com.marvelapp.ui.characters

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CharactersActivityModule {

    @ContributesAndroidInjector
    abstract fun providesMainActivity(): CharactersActivity
}