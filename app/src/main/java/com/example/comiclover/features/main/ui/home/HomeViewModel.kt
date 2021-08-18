package com.example.comiclover.features.main.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comiclover.core.arch.onFailure
import com.example.comiclover.core.arch.onSuccess
import com.example.comiclover.features.main.data.dto.AllCharactersDto
import com.example.comiclover.features.main.data.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {
    private val _state = MutableStateFlow<HomeState>(HomeState.Nothing)
    val state: StateFlow<HomeState> = _state

    init {
        loadAllCharacters()
    }

    private fun setState(fn: () -> HomeState) {
        _state.value = fn()
    }

    private fun loadAllCharacters() {
        setState { HomeState.Loading }
        viewModelScope.launch {
            repository.getAllCharacters()
                .onSuccess { setState { HomeState.Success(it) } }
                .onFailure { setState { HomeState.Error(it.toString()) } }
        }
    }
}

sealed class HomeState {
    object Nothing : HomeState()
    object Loading : HomeState()
    data class Success(val data: AllCharactersDto) : HomeState()
    data class Error(val message: String) : HomeState()
}