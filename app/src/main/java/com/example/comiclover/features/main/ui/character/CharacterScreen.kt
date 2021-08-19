package com.example.comiclover.features.main.ui.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
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
    Box(Modifier.fillMaxSize().background(PrimaryBlack)) {
        Box(
            modifier = Modifier
                .aspectRatio(0.6f)
                .fillMaxSize()
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
            characterDto.characteristics?.let { Characteristics(characteristics = it, modifier = Modifier.fillMaxWidth().padding(vertical = Padding.defaultVertical)) }
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
                )
            )
        )
    }
}