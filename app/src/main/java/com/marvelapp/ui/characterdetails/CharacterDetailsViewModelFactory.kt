package com.marvelapp.ui.characterdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.domain.usecase.characterdetails.GetCharacterDetailsUseCase

@Suppress("UNCHECKED_CAST")
class CharacterDetailsViewModelFactory(private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
            return CharacterDetailsViewModel(
                getCharacterDetailsUseCase
            ) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}