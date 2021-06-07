package com.example.comiclover.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.comiclover.ui.home.HomeScreen
import com.example.comiclover.ui.home.HomeViewModel
import com.example.comiclover.ui.theme.ComicLoverTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeViewModel by viewModels<HomeViewModel>()

        setContent {
            ComicLoverTheme {
                HomeScreen(viewModel = homeViewModel)
            }
        }
    }
}
