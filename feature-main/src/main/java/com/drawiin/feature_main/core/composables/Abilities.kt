package com.drawiin.comiclover.features.main.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.drawiin.feature_main.data.dto.AbilitiesDto
import com.drawiin.common_ui.theme.Padding
import com.drawiin.common_ui.theme.PrimaryWhite

@Composable
fun Abilities(modifier: Modifier = Modifier, abilities: AbilitiesDto) {
    Column(modifier) {
        AbilitiesProgress(progress = abilities.agility, title = "Força")
        AbilitiesProgress(progress = abilities.intelligence, title = "Inteligência")
        AbilitiesProgress(progress = abilities.agility, title = "Agilidade")
        AbilitiesProgress(progress = abilities.endurance, title = "Resistência")
        AbilitiesProgress(progress = abilities.velocity, title = "Velocidade")
    }
}

@Composable
fun AbilitiesProgress(progress: Int, title: String) {
    val animatedProgress by animateFloatAsState(
        targetValue = (progress / 100f),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    Row(Modifier.fillMaxWidth().padding(bottom = Padding.defaultHorizontal), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            style = MaterialTheme.typography.caption.copy(color = PrimaryWhite),
            modifier = Modifier.weight(3f)
        )
        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .weight(7f)
                .clip(MaterialTheme.shapes.large),
            color = PrimaryWhite,
            backgroundColor = PrimaryWhite.copy(alpha = 0.25f)
        )
    }
}

@Preview
@Composable
fun PreviewAbilities() {
    Abilities(
        abilities = AbilitiesDto(
            velocity = 70,
            intelligence = 40,
            force = 90,
            endurance = 10,
            agility = 100
        )
    )
}