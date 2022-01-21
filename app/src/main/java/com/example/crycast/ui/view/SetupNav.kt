package com.example.crycast.ui.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crycast.ui.Screen

@Composable
fun SetupNav(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(route = Screen.Splash.route){
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.Scaffold.route){
            GetMainScaffold(navController)
        }
        composable(route = Screen.ViewConversation.route){
            ViewConversation(navController)
        }
        composable(route = Screen.ViewProfile.route){
            ProfileView(controller = navController)
        }
        composable(route = Screen.CreateUser.route){
            crearUsuario(navController)
        }
    }
}