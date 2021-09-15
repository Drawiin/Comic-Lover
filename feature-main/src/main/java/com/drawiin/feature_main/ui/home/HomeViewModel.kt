package com.drawiin.feature_main.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drawiin.feature_main.data.dto.AllCharactersDto
import com.drawiin.core.arch.onFailure
import com.drawiin.core.arch.onSuccess
import com.drawiin.feature_main.data.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {
    private val _charactersState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeState: StateFlow<HomeState> = _charactersState

    init {
        loadAllCharacters()
    }

    private fun setState(fn: () -> HomeState) {
        _charactersState.value = fn()
    }

    private fun loadAllCharacters() {
        setState { HomeState.Loading }
        viewModelScope.launch {
            delay(4000L)
            repository.getAllCharacters()
                .onSuccess { setState { HomeState.Success(it) } }
                .onFailure { setState { HomeState.Error(it.toString()) } }
        }
    }
}

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val data: AllCharactersDto) : HomeState()
    data class Error(val message: String) : HomeState()
}