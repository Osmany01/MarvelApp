package com.marvelapp.ui.characterdetails.di

import com.example.domain.domain.repositories.CharacterDetailsRepository
import com.example.domain.domain.usecase.characterdetails.GetCharacterDetailsUseCase
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsModule {

    @Provides
    fun providesGetUserUseCase(repo: CharacterDetailsRepository): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(repo)
    }

}