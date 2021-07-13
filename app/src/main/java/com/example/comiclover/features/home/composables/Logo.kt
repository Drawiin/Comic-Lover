package com.example.comiclover.features.home.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.comiclover.R

@Composable
fun Logo() {
    Surface(
        color = MaterialTheme.colors.primary
    ) {
        Text(
            text = stringResource(id = R.string.app_name).uppercase(),
            style = MaterialTheme.typography.h2
        )
    }
}