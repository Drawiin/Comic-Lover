package com.drawiin.feature_main.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.drawiin.feature_main.core.composables.CharactersSection
import com.drawiin.feature_main.data.dto.AllCharactersDto
import com.drawiin.feature_main.data.dto.Character
import com.drawiin.common_ui.composables.DefaultErrorMessage
import com.drawiin.common_ui.theme.*
import com.drawiin.feature_main.R
import com.drawiin.feature_main.core.composables.AppBar
import com.drawiin.feature_main.core.composables.FilterCategory
import com.drawiin.feature_main.core.composables.FilterSelection
import com.drawiin.feature_main.core.composables.PlaceholderBox
import com.drawiin.feature_main.util.SetStatusBarConfig
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun HomeScreen(viewModel: HomeViewModel, navToCharacterDetails: (Character) -> Unit) {
    SetStatusBarConfig {
        color = Color.Transparent
        darkIcons = true
    }
    val homeState: HomeState by viewModel.homeState.collectAsState()
    HomeContent(homeState = homeState, navToCharacterDetails = navToCharacterDetails)
}

@Composable
fun HomeContent(homeState: HomeState, navToCharacterDetails: (Character) -> Unit) {
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .statusBarsPadding(),
        topBar = {
            AppBar()
        },
    ) { contentPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(contentPadding)) {
            when (homeState) {
                is HomeState.Error -> DefaultErrorMessage(message = homeState.message)
                is HomeState.Loading -> HomeLoading()
                is HomeState.Success -> HomeSuccess(
                    state = homeState,
                    onCharacterClicked = navToCharacterDetails
                )
            }
        }
    }
}

@Composable
private fun HomeSuccess(state: HomeState.Success, onCharacterClicked: (Character) -> Unit) {
    val categories = remember {
        FilterCategory::class.nestedClasses
            .map { it.objectInstance as FilterCategory }
    }
    val scroll = rememberScrollState()
    Column(
        Modifier
            .verticalScroll(scroll, true)
            .navigationBarsPadding(),
    ) {
        Column(
            Modifier.padding(
                horizontal = Padding.defaultHorizontal
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

@Composable
fun CharactersCategories(categories: AllCharactersDto, onCharacterClicked: (Character) -> Unit) {
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


