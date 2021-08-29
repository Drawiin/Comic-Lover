package com.drawiin.feature_main.util

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetStatusBarConfig(buildConfig: StatusBarConfigBuilder.() -> Unit) {
    val configs = StatusBarConfigBuilder(MaterialTheme.colors.primary)
    configs.buildConfig()

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = configs.color,
            darkIcons = configs.darkIcons
        )
    }
}

data class StatusBarConfigBuilder(
    var color: Color,
    var darkIcons: Boolean = color.luminance() > 0.5f
)

