package com.drawiin.feature_main.core.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.drawiin.comiclover.features.main.composables.CharacterCard
import com.drawiin.feature_main.data.dto.Character
import com.drawiin.common_ui.theme.*
import com.drawiin.feature_main.R

@Composable
fun CharactersSection(
    modifier: Modifier = Modifier,
    title: String,
    charactersList: List<Character>,
    onCharacter: (Character) -> Unit
) {
    Column(modifier) {
        Row(
            modifier = Modifier.padding(Padding.defaultHorizontal).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = title, style = MaterialTheme.typography.h4.copy(color = PrimaryRed))
            Text(
                text = stringResource(id = R.string.label_see_all),
                style = MaterialTheme.typography.h6.copy(color = PrimaryGrey)
            )
        }
        Spacer(modifier = Modifier.height(height = Spacing.medium))
        LazyRow(contentPadding = PaddingValues(horizontal = Padding.defaultHorizontal)) {
            items(charactersList) { character ->
                CharacterCard(
                    character = character,
                    modifier = Modifier
                        .size(
                            width = Width.characterWidth,
                            height = Height.characterHeight
                        )
                        .clip(MaterialTheme.shapes.large)
                ){onCharacter(character)}
                Spacer(modifier = Modifier.width(Spacing.medium))
            }
        }
    }
}