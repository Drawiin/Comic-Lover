package com.drawiin.feature_main.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drawiin.core.arch.onFailure
import com.drawiin.core.arch.onSuccess
import com.drawiin.comiclover.features.main.data.dto.AllCharactersDto
import com.drawiin.feature_main.data.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {
    private val _charactersState = MutableStateFlow<CharactersState>(CharactersState.Nothing)
    val charactersState: StateFlow<CharactersState> = _charactersState

    init {
        loadAllCharacters()
    }

    private fun setState(fn: () -> CharactersState) {
        _charactersState.value = fn()
    }

    private fun loadAllCharacters() {
        setState { CharactersState.Loading }
        viewModelScope.launch {
            repository.getAllCharacters()
                .onSuccess { setState { CharactersState.Success(it) } }
                .onFailure { setState { CharactersState.Error(it.toString()) } }
        }
    }
}

sealed class CharactersState {
    object Nothing : CharactersState()
    object Loading : CharactersState()
    data class Success(val data: AllCharactersDto) : CharactersState()
    data class Error(val message: String) : CharactersState()
}