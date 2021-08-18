package com.example.comiclover.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comiclover.features.main.ui.home.HomeScreen
import com.example.comiclover.features.main.ui.home.HomeViewModel
import com.example.comiclover.commoniu.theme.ComicLoverTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            ComicLoverTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.Home.route
                ) {
                    composable(Routes.Home.route) {
                        val homeViewModel: HomeViewModel = hiltViewModel()
                        HomeScreen(viewModel = homeViewModel)
                    }
                }

            }
        }
    }
}
