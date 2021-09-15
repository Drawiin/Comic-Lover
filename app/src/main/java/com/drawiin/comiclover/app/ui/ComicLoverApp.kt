package com.drawiin.comiclover.app.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.drawiin.common_ui.theme.ComicLoverTheme
import com.drawiin.core.arch.NavigationRoute
import com.drawiin.feature_main.addMainNavGraph
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@ExperimentalAnimationApi
@Composable
fun ComicLoverApp() {
    val navController = rememberAnimatedNavController()
    ComicLoverTheme {
        ProvideWindowInsets {
            AnimatedNavHost(
                navController = navController,
                startDestination = AppRoutes.Main.routeName
            ) {
                addMainNavGraph(routeName = AppRoutes.Main.routeName, navController = navController)
            }
        }
    }
}

sealed class AppRoutes(override val routeName: String) : NavigationRoute {
    object Main : AppRoutes("main")
}