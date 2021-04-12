package com.marvelapp.ui.characters.di

import com.example.domain.domain.usecase.characters.GetCharactersUseCase
import com.example.domain.domain.repositories.CharactersRepository
import dagger.Module
import dagger.Provides

@Module
class CharactersModule {

    @Provides
    fun providesGetUserUseCase(repo: CharactersRepository): GetCharactersUseCase {
        return GetCharactersUseCase(repo)
    }
}