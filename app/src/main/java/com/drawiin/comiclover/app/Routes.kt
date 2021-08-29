package com.drawiin.comiclover.app

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.drawiin.comiclover.features.main.data.dto.Character

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object CharacterDetail : Routes("character") {
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
