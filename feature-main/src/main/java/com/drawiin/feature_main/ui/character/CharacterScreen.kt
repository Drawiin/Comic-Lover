package com.drawiin.feature_main.ui.character

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.drawiin.comiclover.features.main.composables.Abilities
import com.drawiin.comiclover.features.main.composables.Characteristics
import com.drawiin.common_ui.composables.NetworkImage
import com.drawiin.common_ui.theme.ComicLoverTheme
import com.drawiin.common_ui.theme.GradientBlack
import com.drawiin.common_ui.theme.Height
import com.drawiin.common_ui.theme.Padding
import com.drawiin.common_ui.theme.PrimaryBlack
import com.drawiin.common_ui.theme.PrimaryWhite
import com.drawiin.common_ui.theme.Spacing
import com.drawiin.common_ui.theme.Width
import com.drawiin.core.constants.BASE_RESOURCE_URL
import com.drawiin.feature_main.R
import com.drawiin.feature_main.core.composables.TransparentAppBar
import com.drawiin.feature_main.data.dto.Character
import com.drawiin.feature_main.util.SetStatusBarConfig
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding


@ExperimentalCoilApi
@Composable
fun CharacterScreen(
    character: Character,
    onBack: () -> Unit
) {
    SetStatusBarConfig {
        color = Color.Transparent
        darkIcons = false
    }
    CharacterContent(character = character, onBack = onBack)
}

@ExperimentalCoilApi
@Composable
fun CharacterContent(
    character: Character,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .background(PrimaryBlack)
                .verticalScroll(scrollState, true)
                .navigationBarsPadding()
        ) {
            CharacterPoster(character = character)
            CharacterDetails(character = character)
        }
        val isScrollNotOnStart = scrollState.value != 0
        NavigationOverlay(onBack = onBack, showBackgroundContrast = isScrollNotOnStart)
    }
}

@Composable
fun NavigationOverlay(onBack: () -> Unit, showBackgroundContrast: Boolean = true) {
    val color by animateColorAsState(
        targetValue = if (showBackgroundContrast) Color.Black.copy(alpha = 0.8f) else Color.Transparent,
        animationSpec = tween(
            durationMillis = 600,
            delayMillis = 50,
            easing = LinearOutSlowInEasing
        )
    )
    TransparentAppBar(
        Modifier
            .fillMaxWidth()
            .background(color)
            .statusBarsPadding()
            .padding(horizontal = Padding.defaultHorizontal, vertical = Padding.defaultVertical),
        onLeadingClicked = onBack
    )
}

@ExperimentalCoilApi
@Composable
private fun CharacterPoster(character: Character) {
    Box(
        modifier = Modifier
            .aspectRatio(0.6f)
            .fillMaxWidth()
    ) {
        NetworkImage(
            url = character.imagePath?.replace("./", BASE_RESOURCE_URL) ?: "",
            contentDescription = character.name,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillHeight
        )
        Box(
            modifier = Modifier
                .background(GradientBlack)
                .fillMaxSize()
        )
        CharacterPosterCharacteristics(character)
    }
}

@ExperimentalCoilApi
@Composable
fun CharacterDetails(character: Character) {
    character.biography?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.h6.copy(color = PrimaryWhite),
            modifier = Modifier.padding(horizontal = Padding.defaultHorizontal)
        )
    }
    character.abilities?.let {
        Spacer(modifier = Modifier.height(Spacing.default))
        Text(
            text = stringResource(id = R.string.character_screen_habilities),
            style = MaterialTheme.typography.h4.copy(color = PrimaryWhite),
            modifier = Modifier
                .padding(horizontal = Padding.defaultHorizontal)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Abilities(
            abilities = it,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.defaultHorizontal)
        )
    }
    character.movies?.let { movies ->
        Spacer(modifier = Modifier.height(Spacing.default))
        Text(
            text = stringResource(id = R.string.character_screen_movies),
            style = MaterialTheme.typography.h4.copy(color = PrimaryWhite),
            modifier = Modifier
                .padding(horizontal = Padding.defaultHorizontal)
        )
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = Padding.defaultHorizontal)) {
            items(movies) { movieUrl ->
                NetworkImage(
                    url = movieUrl.replace("./", BASE_RESOURCE_URL),
                    contentDescription = movieUrl,
                    modifier = Modifier
                        .size(
                            width = Width.characterWidth,
                            height = Height.characterHeight
                        )
                        .clip(MaterialTheme.shapes.large)
                )
                Spacer(modifier = Modifier.width(Spacing.medium))
            }
        }
    }
}

@Composable
fun CharacterPosterCharacteristics(character: Character) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        Column(
            Modifier.padding(
                horizontal = Padding.defaultVertical
            )
        ) {
            Text(
                text = character.alterEgo ?: "",
                style = MaterialTheme.typography.body1.copy(color = PrimaryWhite)
            )
            Text(
                text = character.name ?: "",
                style = MaterialTheme.typography.h1.copy(color = PrimaryWhite)
            )
            character.characteristics?.let {
                Characteristics(
                    characteristics = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Padding.defaultVertical)
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Preview(showSystemUi = true)
@Composable
fun CharactersPreview() {
    ComicLoverTheme {
        CharacterScreen(character = Character()) {}
    }
}