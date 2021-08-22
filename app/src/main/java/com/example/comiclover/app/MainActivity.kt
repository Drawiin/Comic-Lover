package com.example.comiclover.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comiclover.commoniu.theme.ComicLoverTheme
import com.example.comiclover.features.main.data.dto.*
import com.example.comiclover.features.main.ui.character.CharacterScreen
import com.example.comiclover.features.main.ui.home.HomeViewModel
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
                                ),
                                biography = "O Pantera Negra é o título cerimonial atribuído ao chefe da Tribo Pantera da avançada nação africana de Wakanda. Além de governar o país, ele também é chefe de suas várias tribos (coletivamente conhecida como Wakandas). O uniforme do Pantera é um símbolo oficial (chefe de estado) e é usado mesmo durante missões diplomáticas. O Pantera é um título hereditário, mas ainda é preciso ganhar um desafio. No passado distante, um enorme meteorito maciço composto de vibranium - elemento que absorve o som, entre outras propriedades especiais - caiu em Wakanda, e é desenterrado uma geração antes dos eventos do presente.",
                                abilities = AbilitiesDto(
                                    velocity = 70,
                                    intelligence = 40,
                                    force = 90,
                                    endurance = 10,
                                    agility = 100
                                ), movies = listOf(
                                    "./movies/captain-america-3.jpg",
                                    "./movies/black-panther.jpg",
                                    "./movies/avengers-3.jpg",
                                    "./movies/avengers-4.jpg"
                                )
                            )
                        )
                    }
                }

            }
        }
    }
}
