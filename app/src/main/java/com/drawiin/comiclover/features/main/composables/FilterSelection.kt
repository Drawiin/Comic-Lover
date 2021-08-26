package com.drawiin.comiclover.features.main.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.drawiin.comiclover.R
import com.drawiin.common_ui.theme.*

@Composable
fun FilterSelection(categories: List<FilterCategory>){
    Row {
        categories.forEach { category ->
            FilterItem(category = category, onClick= {})
            Spacer(modifier = Modifier.width(12.dp))
        }
    }

}

sealed class  FilterCategory(
    @DrawableRes val icon: Int,
    @StringRes val description: Int,
    val backgroundGradient: Brush,
) {
    object Hero: FilterCategory(
        icon = R.drawable.ic_hero,
        description = R.string.description_hero,
        backgroundGradient = GradientBlue
    )
    object Villain: FilterCategory(
        icon = R.drawable.ic_villain,
        description = R.string.description_villain,
        backgroundGradient = GradientRed
    )
    object AntiHero: FilterCategory(
        icon = R.drawable.ic_antihero,
        description = R.string.description_antihero,
        backgroundGradient = GradientPurple
    )
    object Alien: FilterCategory(
        icon = R.drawable.ic_alien,
        description = R.string.description_alien,
        backgroundGradient = GradientGreen
    )
    object Human: FilterCategory(
        icon = R.drawable.ic_human,
        description = R.string.description_human,
        backgroundGradient = GradientPink
    )
}