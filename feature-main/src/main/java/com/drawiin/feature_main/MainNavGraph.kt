package com.drawiin.feature_main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import coil.annotation.ExperimentalCoilApi
import com.drawiin.core.arch.NavigationRoute
import com.drawiin.feature_main.ui.character.CharacterScreen
import com.drawiin.feature_main.ui.home.HomeScreen
import com.drawiin.feature_main.ui.home.HomeViewModel
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.drawiin.feature_main.data.dto.Character


@ExperimentalCoilApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addMainNavGraph(navController: NavHostController, routeName: String) {
    navigation(route = routeName, startDestination = MainRoutes.Home.routeName) {
        composable(
            MainRoutes.Home.routeName,
        ) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = homeViewModel,
                navToCharacterDetails = { character ->
                    MainRoutes.CharacterDetail.navigateTo(character, navController)
                }
            )
        }

        composable(
            route = MainRoutes.CharacterDetail.routeName
        ) { backStackEntry ->
            val character = MainRoutes.CharacterDetail.getNavArgs(backStackEntry)
            character?.let {
                CharacterScreen(
                    character = character,
                    onBack = { navController.popBackStack() })
            }
        }
    }
}

sealed class MainRoutes(override val routeName: String): NavigationRoute {
    object Home : MainRoutes("home")
    object CharacterDetail : MainRoutes("character") {
        private const val characterArg = "character"

        val navArgs
            get() = listOf(navArgument(characterArg) {
                type = NavType.ParcelableType(Character::class.java)
            })

        fun navigateTo(character: Character, navController: NavHostController) {
            navController.navigate(routeName)
            navController.currentBackStackEntry?.arguments?.putParcelable(
                characterArg,
                character
            )
        }

        fun getNavArgs(backStackEntry: NavBackStackEntry) =
            backStackEntry.arguments?.getParcelable<Character>(characterArg)
    }
}