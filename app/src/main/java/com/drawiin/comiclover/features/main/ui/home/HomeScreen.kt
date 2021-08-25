package com.drawiin.comiclover.features.main.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.drawiin.comiclover.R
import com.drawiin.comiclover.commoniu.composables.AppBar
import com.drawiin.comiclover.commoniu.composables.CharactersSection
import com.drawiin.comiclover.commoniu.composables.FilterCategory
import com.drawiin.comiclover.commoniu.composables.FilterSelection
import com.drawiin.comiclover.commoniu.theme.Padding
import com.drawiin.comiclover.commoniu.theme.PrimaryGrey
import com.drawiin.comiclover.commoniu.theme.Spacing
import com.drawiin.comiclover.features.main.data.dto.AllCharactersDto
import com.drawiin.comiclover.features.main.data.dto.CharacterDto
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(viewModel: HomeViewModel, onNavToCharacterDetails: (CharacterDto) -> Unit) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        with(systemUiController) {
            setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = true
            )
        }
    }

    val categories = FilterCategory::class.nestedClasses
        .map { it.objectInstance as FilterCategory }
    val charactersState: CharactersState by viewModel.charactersState.collectAsState()


    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .padding(top = Padding.topSafe),
        topBar = {
            AppBar(onLeadingClicked = {}, onLeaTrailingClicked = {})
        },
    ) {
        val scroll = rememberScrollState()
        Column(
            Modifier
                .verticalScroll(scroll, true)
                .padding(top = Padding.topSafe, bottom = Padding.bottomSafe),
        ) {
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
            CharactersBody(state = charactersState) { characterDto ->
                onNavToCharacterDetails(
                    characterDto
                )
            }
        }

    }
}

@Composable
fun CharactersBody(state: CharactersState, onCharacter: (CharacterDto) -> Unit) {
    when (state) {
        is CharactersState.Error -> Text(text = "Woooops")
        is CharactersState.Loading -> Column(Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
        is CharactersState.Success -> CharactersCategories(state.data, onCharacter = onCharacter)
        is CharactersState.Nothing -> {
        }
    }
}


@Composable
fun CharactersCategories(categories: AllCharactersDto, onCharacter: (CharacterDto) -> Unit) {
    Column(Modifier.padding(bottom = Padding.defaultVertical)) {
        CharactersSection(
            title = "Heróis", charactersList = categories.heroes,
            onCharacter = onCharacter
        )
        CharactersSection(
            title = "Vilões",
            charactersList = categories.villains,
            onCharacter = onCharacter
        )
        CharactersSection(
            title = "Anti-heróis",
            charactersList = categories.antiHeroes,
            onCharacter = onCharacter
        )
        CharactersSection(
            title = "Alienigenas",
            charactersList = categories.aliens,
            onCharacter = onCharacter
        )
        CharactersSection(
            title = "Humanos",
            charactersList = categories.humans,
            onCharacter = onCharacter
        )
    }
}
