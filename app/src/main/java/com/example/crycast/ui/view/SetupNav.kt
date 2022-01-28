package com.example.crycast.ui.view

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
    var dataStoreViewModel: DataStoreViewModel = viewModel()
    var mainViewModel: MainViewModel = viewModel()
    var scope = rememberCoroutineScope()

    var idUser = dataStore.dataStoreUserId.collectAsState(initial = "").value
    var mail = dataStore.dataStoreUserMail.collectAsState(initial = "").value
    var name = dataStore.dataStoreUserName.collectAsState(initial = "").value

    currentUser.id = idUser!!
    currentUser.mail = mail!!
    currentUser.name = name!!

    navHostController = navController
    NavHost(
        navController = navController,
        startDestination =
        if(idUser == "0"){
            Screen.LoginScreen.route
        } else {
            Screen.Splash.route
        }

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