package com.marvelapp.ui.characterdetails.di

import com.marvelapp.ui.characterdetails.CharacterDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
abstract class CharacterDetailsActivityModule {

    @ContributesAndroidInjector
    abstract fun providesCharacterDetailsActivity(): CharacterDetailsActivity
}