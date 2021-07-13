package com.example.comiclover.app

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object ComicDetails : Routes("comic/{id}") {
        fun createRoute(id: Int) = "comic/${id}"
    }
}
