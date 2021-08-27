package com.drawiin.common_ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.drawiin.common_ui.theme.Padding

@Composable
fun DefaultErrorMessage(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .padding(Padding.defaultHorizontal)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}