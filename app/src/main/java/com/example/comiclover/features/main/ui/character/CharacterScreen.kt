package com.example.comiclover.features.main.ui.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comiclover.commoniu.composables.Abilities
import com.example.comiclover.commoniu.composables.Characteristics
import com.example.comiclover.commoniu.composables.NetworkImage
import com.example.comiclover.commoniu.composables.TransparentAppBar
import com.example.comiclover.commoniu.theme.*
import com.example.comiclover.core.constants.BASE_RESOURCE_URL
import com.example.comiclover.features.main.data.dto.CharacterDto
import com.example.comiclover.features.main.data.dto.CharacteristicsDto
import com.example.comiclover.features.main.data.dto.HeightDto
import com.example.comiclover.features.main.data.dto.WeightDto
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CharacterScreen(
    characterDto: CharacterDto,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .background(PrimaryBlack)
            .verticalScroll(scrollState, true)
            .padding(bottom = Padding.topSafe)
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(0.6f)
                .fillMaxWidth()
        ) {
            NetworkImage(
                url = characterDto.imagePath?.replace("./", BASE_RESOURCE_URL) ?: "",
                contentDescription = characterDto.name,
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillHeight
            )
            Box(
                modifier = Modifier
                    .background(GradientBlack)
                    .fillMaxSize()
            )
            CharacterScreenBody(characterDto, onBack)
        }
        characterDto.biography?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.h6.copy(color = PrimaryWhite),
                modifier = Modifier.padding(horizontal = Padding.defaultHorizontal)
            )
        }
        characterDto.abilities?.let {
            Spacer(modifier = Modifier.height(Spacing.default))
            Text(
                text = "Habilidades",
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
        characterDto.movies?.let { movies ->
            Spacer(modifier = Modifier.height(Spacing.default))
            Text(
                text = "Filmes",
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
}

@Composable
fun CharacterScreenBody(characterDto: CharacterDto, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        TransparentAppBar(
            Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.7f))
                .padding(top = Padding.topSafe)
                .padding(horizontal = Padding.defaultHorizontal, vertical = Padding.defaultVertical)

        ) {onBack()}
        Column(
            Modifier.padding(
                horizontal = Padding.defaultVertical
            )
        ) {
            Text(
                text = characterDto.alterEgo ?: "",
                style = MaterialTheme.typography.body1.copy(color = PrimaryWhite)
            )
            Text(
                text = characterDto.name ?: "",
                style = MaterialTheme.typography.h1.copy(color = PrimaryWhite)
            )
            characterDto.characteristics?.let {
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