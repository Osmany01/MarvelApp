package com.marvelapp.ui.characterdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.usecase.characterdetails.GetCharacterDetailsUseCase
import com.marvelapp.R
import com.marvelapp.ui.common.GENERIC_ERROR_MESSAGE
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {

    private val _characterDetailsUiState =
        MutableStateFlow<CharacterDetailsUiState>(CharacterDetailsUiState.Empty)
    val characterDetailsUiState get() = _characterDetailsUiState

    fun getCharacterDetails(characterId: Int) = viewModelScope.launch {

        getCharacterDetailsUseCase.checkCharacterDetails(characterId).catch {

            _characterDetailsUiState.value = CharacterDetailsUiState.Error(it.message?: GENERIC_ERROR_MESSAGE)
        }
            .onEach {  _characterDetailsUiState.value = CharacterDetailsUiState.Error(
                GENERIC_ERROR_MESSAGE)}
            .collect { characterDetails ->
            if (characterDetails != null) _characterDetailsUiState.value =
                CharacterDetailsUiState.Success(characterDetails)
            else _characterDetailsUiState.value = CharacterDetailsUiState.Empty
        }
    }

    sealed class CharacterDetailsUiState {
        data class Success(val characterDetails: CharacterDetails) : CharacterDetailsUiState()
        object Loading : CharacterDetailsUiState()
        data class Error(val message: String): CharacterDetailsUiState()
        object Empty : CharacterDetailsUiState()
    }
}