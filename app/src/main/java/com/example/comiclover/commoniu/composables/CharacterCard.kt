package com.example.comiclover.commoniu.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comiclover.commoniu.theme.*
import com.example.comiclover.core.constants.BASE_RESOURCE_URL
import com.example.comiclover.network.dto.CharacterDto

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    characterDto: CharacterDto
) {
    Box(modifier) {
        NetworkImage(
            url = characterDto.imagePath ?: "",
            contentDescription = characterDto.name,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .background(GradientBlack)
                .fillMaxSize()
        )

        Column(
            Modifier
                .padding(Padding.button)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = characterDto.alterEgo ?: "", style = MaterialTheme.typography.body2.copy(
                    PrimaryWhite
                )
            )
            Text(
                text = characterDto.name ?: "", style = MaterialTheme.typography.h3.copy(
                    PrimaryWhite
                )
            )
        }
    }
}


@Composable
@Preview
fun PreviewCharacterCard() {
    ComicLoverTheme {
        Column(Modifier.padding(32.dp)) {
            CharacterCard(
                Modifier
                    .size(width = Width.characterWidth, height = Height.characterHeight)
                    .clip(MaterialTheme.shapes.large),
                characterDto = CharacterDto(
                    name = "Pantera Negra",
                    imagePath = "./chars/black-panther.png".replace("./", BASE_RESOURCE_URL),
                    alterEgo = "T'Challa"
                )
            )
        }

    }
}