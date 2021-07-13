package com.example.comiclover.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comiclover.features.home.composables.Logo

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val list = viewModel.movies.value
    Scaffold(
        topBar = { Row(horizontalArrangement = Arrangement.Center) { Logo() } }
    ) {
        LazyColumn {
            items(list) { comic ->
                Text(text = comic.title ?: "", style = MaterialTheme.typography.body1)
            }
        }
    }

}