package com.drawiin.comiclover.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.drawiin.common_ui.theme.ComicLoverTheme
import com.drawiin.feature_main.ui.character.CharacterScreen
import com.drawiin.feature_main.ui.home.HomeScreen
import com.drawiin.feature_main.ui.home.HomeViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            ComicLoverTheme {
                ProvideWindowInsets {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Home.route
                    ) {

                        composable(
                            Routes.Home.route,
                        ) {
                            val homeViewModel: HomeViewModel = hiltViewModel()
                            HomeScreen(
                                viewModel = homeViewModel,
                                navToCharacterDetails = { character ->
                                    Routes.CharacterDetail.navigateTo(character, navController)
                                }
                            )
                        }

                        composable(
                            route = Routes.CharacterDetail.route,
                            arguments = Routes.CharacterDetail.navArgs
                        ) { backStackEntry ->
                            val character = Routes.CharacterDetail.getNavArgs(backStackEntry)
                            character?.let {
                                CharacterScreen(
                                    character = character,
                                    onBack = { navController.popBackStack() })
                            }
                        }
                    }
                }
            }
        }
    }
}
