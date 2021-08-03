package com.example.comiclover.commoniu.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.comiclover.R
import com.example.comiclover.commoniu.theme.ComicLoverTheme
import com.example.comiclover.commoniu.theme.GradientBlue
import com.example.comiclover.commoniu.theme.Padding
import com.example.comiclover.commoniu.theme.Size

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