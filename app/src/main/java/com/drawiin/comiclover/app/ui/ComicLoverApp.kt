package com.drawiin.comiclover.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.drawiin.common_ui.theme.ComicLoverTheme
import com.drawiin.core.arch.NavigationRoute
import com.drawiin.feature_main.addMainNavGraph
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun ComicLoverApp() {
    val navController = rememberNavController()
    ComicLoverTheme {
        ProvideWindowInsets {
            NavHost(
                navController = navController,
                startDestination = AppRoutes.Main.routeName
            ) {
                addMainNavGraph(routeName = AppRoutes.Main.routeName, navController = navController)
            }
        }
    }
}

sealed class AppRoutes(override val routeName: String): NavigationRoute {
    object Main : AppRoutes("main")
}