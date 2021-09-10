package com.drawiin.comiclover.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.drawiin.comiclover.features.main.data.dto.Character
import com.drawiin.common_ui.theme.ComicLoverTheme
import com.drawiin.feature_main.ui.character.CharacterScreen
import com.drawiin.feature_main.ui.home.HomeScreen
import com.drawiin.feature_main.ui.home.HomeViewModel
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun ComicLoverApp() {
    val navController = rememberNavController()
    ComicLoverTheme {
        ProvideWindowInsets {
            NavHost(
                navController = navController,
                startDestination = AppRoutes.Home.route
            ) {
                composable(
                    AppRoutes.Home.route,
                ) {
                    val homeViewModel: HomeViewModel = hiltViewModel()
                    HomeScreen(
                        viewModel = homeViewModel,
                        navToCharacterDetails = { character ->
                            AppRoutes.CharacterDetail.navigateTo(character, navController)
                        }
                    )
                }

                composable(
                    route = AppRoutes.CharacterDetail.route,
                    arguments = AppRoutes.CharacterDetail.navArgs
                ) { backStackEntry ->
                    val character = AppRoutes.CharacterDetail.getNavArgs(backStackEntry)
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

sealed class AppRoutes(val route: String) {
    object Home : AppRoutes("home")
    object CharacterDetail : AppRoutes("character") {
        private const val characterArg = "character"

        val navArgs
            get() = listOf(navArgument(characterArg) {
                type = NavType.ParcelableType(Character::class.java)
            })

        fun navigateTo(character: Character, navController: NavController) {
            navController.navigate(route)
            navController.currentBackStackEntry?.arguments?.putParcelable(
                characterArg,
                character
            )
        }

        fun getNavArgs(backStackEntry: NavBackStackEntry) =
            backStackEntry.arguments?.getParcelable<Character>(characterArg)
    }
}