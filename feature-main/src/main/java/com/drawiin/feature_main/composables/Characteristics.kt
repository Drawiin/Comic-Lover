package com.drawiin.comiclover.features.main.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.drawiin.comiclover.features.main.data.dto.CharacteristicsDto
import com.drawiin.comiclover.features.main.data.dto.HeightDto
import com.drawiin.comiclover.features.main.data.dto.WeightDto
import com.drawiin.common_ui.theme.ComicLoverTheme
import com.drawiin.common_ui.theme.PrimaryWhite
import com.drawiin.feature_main.R

@Composable
fun Characteristics(modifier: Modifier = Modifier, characteristics: CharacteristicsDto) {
    Row(modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        CharacteristicsItem(characteristic = CharacteristicsTypes.Birth(characteristics.birth))
        CharacteristicsItem(characteristic = CharacteristicsTypes.Height(characteristics.height))
        CharacteristicsItem(characteristic = CharacteristicsTypes.Universe(characteristics.universe))
        CharacteristicsItem(characteristic = CharacteristicsTypes.Weight(characteristics.weight))
    }
}

@Composable
fun CharacteristicsItem(characteristic: CharacteristicsTypes) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = characteristic.icon),
            contentDescription = characteristic.formatValue,
            tint = PrimaryWhite
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = characteristic.formatValue,
            style = MaterialTheme.typography.caption.copy(color = PrimaryWhite)
        )
    }
}

sealed class CharacteristicsTypes(val formatValue: String, @DrawableRes val icon: Int) {
    data class Birth(val date: String) : CharacteristicsTypes(date, R.drawable.ic_age)
    data class Height(val height: HeightDto) :
        CharacteristicsTypes(height.value.toString() + height.unity, R.drawable.ic_height)

    data class Universe(val universe: String) :
        CharacteristicsTypes(universe, R.drawable.ic_universe)

    data class Weight(val weight: WeightDto) :
        CharacteristicsTypes(weight.value.toString() + weight.unity, R.drawable.ic_weight)
}

@Composable
@Preview()
fun CharacteristicsPreview() {
    ComicLoverTheme {
        Characteristics(
            Modifier.fillMaxWidth(), characteristics = CharacteristicsDto(
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
    }
}