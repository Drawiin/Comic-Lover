package com.drawiin.feature_main.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.drawiin.comiclover.features.main.composables.CharactersSection
import com.drawiin.comiclover.features.main.data.dto.AllCharactersDto
import com.drawiin.comiclover.features.main.data.dto.CharacterDto
import com.drawiin.common_ui.composables.DefaultErrorMessage
import com.drawiin.common_ui.theme.*
import com.drawiin.feature_main.R
import com.drawiin.feature_main.core.composables.AppBar
import com.drawiin.feature_main.core.composables.FilterCategory
import com.drawiin.feature_main.core.composables.FilterSelection
import com.drawiin.feature_main.core.composables.PlaceholderBox
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(viewModel: HomeViewModel, navToCharacterDetails: (CharacterDto) -> Unit) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        with(systemUiController) {
            setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = true
            )
        }
    }

    val homeState: HomeState by viewModel.homeState.collectAsState()

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .padding(top = Padding.topSafe),
        topBar = {
            AppBar(onLeadingClicked = {}, onLeaTrailingClicked = {})
        },
    ) {
        when (val state = homeState) {
            is HomeState.Error -> DefaultErrorMessage(message = state.message)
            is HomeState.Loading -> HomeLoading()
            is HomeState.Success -> HomeSuccess(
                state = state,
                onCharacterClicked = navToCharacterDetails
            )
        }
    }
}

@Composable
private fun HomeSuccess(state: HomeState.Success, onCharacterClicked: (CharacterDto) -> Unit) {
    val categories = remember {
        FilterCategory::class.nestedClasses
            .map { it.objectInstance as FilterCategory }
    }
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
        CharactersCategories(categories = state.data, onCharacterClicked = onCharacterClicked)
    }
}

@Composable
fun HomeLoading() {
    val categories = remember {
        FilterCategory::class.nestedClasses
            .map { it.objectInstance as FilterCategory }
    }
    Column(
        Modifier
            .padding(top = Padding.topSafe, bottom = Padding.bottomSafe)

    ) {
        Column(
            Modifier.padding(
                horizontal = Padding.defaultHorizontal,
                vertical = Padding.defaultVertical
            )

        ) {
            PlaceholderBox(
                Modifier
                    .fillMaxWidth(0.6f)
                    .height(12.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            PlaceholderBox(
                Modifier
                    .fillMaxWidth(0.7f)
                    .height(20.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            PlaceholderBox(
                Modifier
                    .fillMaxWidth(0.7f)
                    .height(20.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            PlaceholderBox(
                Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(Modifier.fillMaxWidth()) {
                repeat(2) {
                    Column(Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            PlaceholderBox(
                                Modifier
                                    .fillMaxWidth(0.3f)
                                    .height(12.dp)
                            )
                            PlaceholderBox(
                                Modifier
                                    .fillMaxWidth(0.3f)
                                    .height(12.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            repeat(3) {
                                PlaceholderBox(
                                    Modifier
                                        .size(
                                            width = Width.characterWidth,
                                            height = Height.characterHeight
                                        )

                                )
                                Spacer(modifier = Modifier.width(12.dp))
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun CharactersCategories(categories: AllCharactersDto, onCharacterClicked: (CharacterDto) -> Unit) {
    Column(Modifier.padding(bottom = Padding.defaultVertical)) {
        CharactersSection(
            title = "Heróis", charactersList = categories.heroes,
            onCharacter = onCharacterClicked
        )
        CharactersSection(
            title = "Vilões",
            charactersList = categories.villains,
            onCharacter = onCharacterClicked
        )
        CharactersSection(
            title = "Anti-heróis",
            charactersList = categories.antiHeroes,
            onCharacter = onCharacterClicked
        )
        CharactersSection(
            title = "Alienigenas",
            charactersList = categories.aliens,
            onCharacter = onCharacterClicked
        )
        CharactersSection(
            title = "Humanos",
            charactersList = categories.humans,
            onCharacter = onCharacterClicked
        )
    }
}


