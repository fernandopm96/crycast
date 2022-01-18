package com.example.crycast.ui

sealed class Screen(val route: String) {
    object Splash : Screen(route = "splash_screen")
    object Scaffold : Screen(route = "scaffold_screen")
    object ViewConversation : Screen(route = "viewconversation_screen")
}