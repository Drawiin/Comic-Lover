package com.drawiin.feature_main.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.drawiin.common_ui.theme.ComicLoverTheme
import com.drawiin.common_ui.theme.Padding
import com.drawiin.feature_main.R

@Composable
fun AppBar(
    onLeadingClicked: () -> Unit,
    onLeaTrailingClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
            .padding(horizontal = Padding.defaultHorizontal, vertical = Padding.defaultVertical)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = stringResource(
                    id = R.string.description_open_menu
                ),
                tint = MaterialTheme.colors.onSurface
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_marvel_logo),
            contentDescription = stringResource(id = R.string.description_marvel_logo),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary)
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(
                    id = R.string.description_start_search
                ),
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Composable
@Preview
fun Preview() {
    ComicLoverTheme() {
        AppBar({}, {})
    }
}