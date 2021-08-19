package com.example.comiclover.features.main.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.comiclover.R
import com.example.comiclover.commoniu.composables.AppBar
import com.example.comiclover.commoniu.composables.CharactersSection
import com.example.comiclover.commoniu.composables.FilterCategory
import com.example.comiclover.commoniu.composables.FilterSelection
import com.example.comiclover.commoniu.theme.Padding
import com.example.comiclover.commoniu.theme.PrimaryGrey
import com.example.comiclover.commoniu.theme.Spacing
import com.example.comiclover.features.main.data.dto.AllCharactersDto

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val categories = FilterCategory::class.nestedClasses
        .map { it.objectInstance as FilterCategory }
    val charactersState: CharactersState by viewModel.charactersState.collectAsState()

    Scaffold(
        topBar = {
            AppBar(onLeadingClicked = {}, onLeaTrailingClicked = {})
        },
    ) {
        val scroll = rememberScrollState()
        Column(Modifier.verticalScroll(scroll, true)) {
            Column(
                Modifier.padding(
                    horizontal = Padding.defaultHorizontal,
                    vertical = Padding.defaultVertical
                )
            ) {
                Text(
                    text = stringResource(id = R.string.title_welcome_to_marvel_heroes),
                    style = MaterialTheme.typography.h6.copy(color = PrimaryGrey)
                )
                Text(
                    text = stringResource(id = R.string.title_choose_your_character),
                    style = MaterialTheme.typography.h2
                )
                Spacer(modifier = Modifier.height(Spacing.default))
                FilterSelection(categories = categories)

            }
            CharactersBody(state = charactersState)
        }

    }
}

@Composable
fun CharactersBody(state: CharactersState) {
    when (state) {
        is CharactersState.Error -> Text(text = "Woooops")
        is CharactersState.Loading -> Column(Modifier.fillMaxSize()){
            CircularProgressIndicator()
        }
        is CharactersState.Success -> CharactersCategories(state.data)
        is CharactersState.Nothing -> {}
    }
}

@Composable
fun CharactersCategories(categories: AllCharactersDto) {
    Column(Modifier.padding(bottom = Padding.defaultVertical)) {
        CharactersSection(title = "Heróis", characters = categories.heroes)
        CharactersSection(title = "Vilões", characters = categories.villains)
        CharactersSection(title = "Anti-heróis", characters = categories.antiHeroes)
        CharactersSection(title = "Alienigenas", characters = categories.aliens)
        CharactersSection(title = "Humanos", characters = categories.humans)
    }
}
