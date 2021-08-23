package com.example.comiclover.app

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.example.comiclover.features.main.data.dto.CharacterDto

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object CharacterDetail : Routes("character") {
        private const val characterArg = "character"

        val navArgs
            get() = listOf(navArgument(characterArg) {
                type = NavType.ParcelableType(CharacterDto::class.java)
            })

        fun navigateTo(characterDto: CharacterDto, navController: NavController) {
            navController.navigate(route)
            navController.currentBackStackEntry?.arguments?.putParcelable(
                characterArg,
                characterDto
            )
        }

        fun getNavArgs(backStackEntry: NavBackStackEntry) =
            backStackEntry.arguments?.getParcelable<CharacterDto>(characterArg)
    }
}
