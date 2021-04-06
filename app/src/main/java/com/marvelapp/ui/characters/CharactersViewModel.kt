package com.marvelapp.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.usecase.characters.GetCharactersUseCase
import com.example.domain.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase): ViewModel() {

    private val _spinner = MutableStateFlow(true)
    val spinner: StateFlow<Boolean> get() = _spinner

    val characters: Flow<List<Character>> get() = getCharactersUseCase.getCharacters()

    init {
        viewModelScope.launch {
            notifyLastVisible(0)
        }
    }

    suspend fun notifyLastVisible(lastVisible: Int) {
        getCharactersUseCase.checkRequiredNewPage(lastVisible)
        _spinner.value = false
    }
}