package com.example.comiclover.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comiclover.core.arch.onSuccess
import com.example.comiclover.domain.interactor.GetMovies
import com.example.comiclover.domain.model.Comic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getMovies: GetMovies
) : ViewModel() {
    val movies = mutableStateOf(listOf<Comic>())

    init {
        viewModelScope.launch {
            getMovies().onSuccess {
                movies.value = it
            }
        }

    }
}