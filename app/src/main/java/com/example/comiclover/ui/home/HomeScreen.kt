package com.example.comiclover.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val list = viewModel.movies.value
    LazyColumn {
        items(list) { comic ->
            Text(text = comic.title ?: "")
        }
    }
}