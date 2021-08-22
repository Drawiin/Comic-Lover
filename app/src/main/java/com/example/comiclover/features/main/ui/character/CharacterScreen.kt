package com.example.comiclover.features.main.ui.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
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
    characterDto: CharacterDto
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
            CharacterScreenBody(characterDto)
        }
        characterDto.biography?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.h6.copy(color = PrimaryWhite),
                modifier = Modifier.padding(horizontal = Padding.defaultHorizontal)
            )
        }
        characterDto.abilities?.let {
            Abilities(
                abilities = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Padding.defaultHorizontal)
            )
        }
    }
}

@Composable
fun CharacterScreenBody(characterDto: CharacterDto) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        TransparentAppBar(
            Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.7f))
                .padding(top = Padding.topSafe)
                .padding(horizontal = Padding.defaultHorizontal, vertical = Padding.defaultVertical)

        ) {}
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

@Preview
@Composable
fun PreviewCharacterScreen() {
    ComicLoverTheme {
        CharacterScreen(
            characterDto = CharacterDto(
                name = "Pantera Negra",
                imagePath = "./chars/black-panther.png",
                alterEgo = "T'Challa",
                characteristics = CharacteristicsDto(
                    universe = "Terra 69",
                    birth = "90 anos",
                    height = HeightDto(
                        unity = "km",
                        value = 300.0
                    ),
                    weight = WeightDto(
                        unity = "ton",
                        value = 380
                    )
                ),
                biography = "O Pantera Negra é o título cerimonial atribuído ao chefe da Tribo Pantera da avançada nação africana de Wakanda. Além de governar o país, ele também é chefe de suas várias tribos (coletivamente conhecida como Wakandas). O uniforme do Pantera é um símbolo oficial (chefe de estado) e é usado mesmo durante missões diplomáticas. O Pantera é um título hereditário, mas ainda é preciso ganhar um desafio. No passado distante, um enorme meteorito maciço composto de vibranium - elemento que absorve o som, entre outras propriedades especiais - caiu em Wakanda, e é desenterrado uma geração antes dos eventos do presente."
            )
        )
    }
}