package com.drawiin.comiclover.features.main.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.drawiin.common_ui.theme.ComicLoverTheme
import com.drawiin.common_ui.theme.Size
import com.drawiin.feature_main.core.composables.FilterCategory


@Composable
fun FilterItem(
    category: FilterCategory,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier
            .size(Size.button)
            .clip(
                CircleShape
            )
            .background(category.backgroundGradient),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = category.icon),
            contentDescription = stringResource(id = category.description),
            tint = Color.White,
            modifier = Modifier.size(Size.iconLarge)
        )
    }
}

@Preview
@Composable
fun FilterItemPreview() {
    ComicLoverTheme {
        FilterItem(
            FilterCategory.Hero,
            onClick = {}
        )
    }
}