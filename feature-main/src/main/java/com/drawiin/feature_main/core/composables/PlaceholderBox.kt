package com.drawiin.feature_main.core.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder

@Composable
fun PlaceholderBox(modifier: Modifier = Modifier) {
    Box(modifier.placeholder(true, highlight = PlaceholderHighlight.fade()))
}