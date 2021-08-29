package com.drawiin.comiclover.features.main.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.drawiin.comiclover.features.main.data.dto.Character
import com.drawiin.common_ui.composables.NetworkImage
import com.drawiin.common_ui.theme.*
import com.drawiin.core.constants.BASE_RESOURCE_URL

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: () -> Unit
) {
    Box(modifier.clickable { onClick() }) {
        NetworkImage(
            url = character.imagePath?.replace("./", BASE_RESOURCE_URL) ?: "",
            contentDescription = character.name,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .background(GradientDark)
                .fillMaxSize()
        )

        Column(
            Modifier
                .padding(Padding.button)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = character.alterEgo ?: "", style = MaterialTheme.typography.body2.copy(
                    PrimaryWhite
                )
            )
            Text(
                text = character.name ?: "", style = MaterialTheme.typography.h3.copy(
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
                character = Character(
                    name = "Pantera Negra",
                    imagePath = "./chars/black-panther.png",
                    alterEgo = "T'Challa"
                )
            ){}
        }

    }
}