package com.example.comiclover.app

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comiclover.features.main.ui.home.HomeScreen
import com.example.comiclover.features.main.ui.home.HomeViewModel
import com.example.comiclover.commoniu.theme.ComicLoverTheme
import com.example.comiclover.features.main.data.dto.CharacterDto
import com.example.comiclover.features.main.data.dto.CharacteristicsDto
import com.example.comiclover.features.main.data.dto.HeightDto
import com.example.comiclover.features.main.data.dto.WeightDto
import com.example.comiclover.features.main.ui.character.CharacterScreen
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
                    composable(Routes.Home.route) {
                        val homeViewModel: HomeViewModel = hiltViewModel()
//                        HomeScreen(viewModel = homeViewModel)
                        CharacterScreen(
                            characterDto = CharacterDto(
                                name = "Pantera Negra",
                                imagePath = "./chars/black-panther.png",
                                alterEgo = "T'Challa",
                                characteristics = CharacteristicsDto(
                                    universe = "Terra 69",
                                    birth = "90 anos",
                                    height = HeightDto(
                                        unity = "km",
                                        value = 300.0
                                    ),
                                    weight = WeightDto(
                                        unity = "ton",
                                        value = 380
                                    )
                                )
                            )
                        )
                    }
                }

            }
        }
    }
}
