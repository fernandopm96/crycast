package com.example.crycast.ui.view

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crycast.model.DataStoreManager
import com.example.crycast.ui.Screen
import com.example.crycast.viewmodel.DataStoreViewModel
import com.example.crycast.viewmodel.MainViewModel
import kotlinx.coroutines.launch

lateinit var dataStore: DataStoreManager
lateinit var navHostController: NavHostController
@Composable
fun SetupNav(navController: NavHostController){

    var mainViewModel: MainViewModel = viewModel()
    if(!mainViewModel.anyUser()){
        Log.i("USUARIOS", "NO HAY USUARIOS")
        mainViewModel.createSampleUsers()
    } else {
        Log.i("USUARIOS", "HAY USUARIOS")
    }
    navHostController = navController
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route

    ){

        composable(route = Screen.LoginScreen.route){
            LoginScreen()
        }
        composable(route = Screen.CurrentUser.route){
            chooseUser()
        }
        composable(route = Screen.Splash.route){

            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.Scaffold.route){
            GetMainScaffold()
        }
        composable(route = Screen.ViewConversation.route){
            ViewConversation()
        }
        composable(route = Screen.ViewProfile.route){
            ProfileView()
        }
        composable(route = Screen.CreateUser.route){
            crearUsuario()
        }

    }
}