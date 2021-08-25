package com.drawiin.comiclover.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.drawiin.comiclover.commoniu.theme.ComicLoverTheme
import com.drawiin.comiclover.features.main.ui.character.CharacterScreen
import com.drawiin.comiclover.features.main.ui.home.HomeScreen
import com.drawiin.comiclover.features.main.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            ComicLoverTheme {
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
                            onNavToCharacterDetails = { character ->
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
                                characterDto = character,
                                onBack = { navController.popBackStack() })
                        }
                    }
                }

            }
        }
    }
}
