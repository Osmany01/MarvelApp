package com.marvelapp.ui.characterdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.usecase.characterdetails.GetCharacterDetailsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {

    private val _characterDetailsUiState =
        MutableStateFlow<CharacterDetailsUiState>(CharacterDetailsUiState.Empty)
    val characterDetailsUiState get() = _characterDetailsUiState

    fun getCharacterDetails(characterId: Int) = viewModelScope.launch {
        getCharacterDetailsUseCase.checkCharacterDetails(characterId)
        _characterDetailsUiState.value = CharacterDetailsUiState.Loading


        getCharacterDetailsUseCase.getCharacterDetails(characterId).collect { characterDetails ->
            _characterDetailsUiState.value = CharacterDetailsUiState.Success(characterDetails)
        }
    }

    sealed class CharacterDetailsUiState {
        data class Success(val characterDetails: CharacterDetails) : CharacterDetailsUiState()
        object Loading : CharacterDetailsUiState()
        object Empty : CharacterDetailsUiState()
    }
}