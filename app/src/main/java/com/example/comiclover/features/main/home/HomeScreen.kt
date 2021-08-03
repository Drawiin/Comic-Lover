package com.example.comiclover.features.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.comiclover.R
import com.example.comiclover.commoniu.composables.AppBar
import com.example.comiclover.commoniu.composables.FilterCategory
import com.example.comiclover.commoniu.composables.FilterSelection
import com.example.comiclover.commoniu.theme.Padding
import com.example.comiclover.commoniu.theme.PrimaryGrey
import com.example.comiclover.commoniu.theme.Spacing

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val categories = FilterCategory::class.nestedClasses
        .map { it.objectInstance as FilterCategory }

    Scaffold(
        topBar = {
            AppBar(onLeadingClicked = {}, onLeaTrailingClicked = {})
        },
    ) {
        Column(
            Modifier.padding(
                horizontal = Padding.defaultHorizontal,
                vertical = Padding.defaultVertical
            )
        ) {
            Text(
                text = stringResource(id = R.string.title_welcome_to_marvel_heroes),
                style = MaterialTheme.typography.h6.copy(color = PrimaryGrey)
            )
            Text(
                text = stringResource(id = R.string.title_choose_your_character),
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(Spacing.default))
            FilterSelection(categories = categories)
        }
    }

}
