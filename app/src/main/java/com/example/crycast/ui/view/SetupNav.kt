package com.example.crycast.ui.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crycast.ui.Screen
import com.example.crycast.viewmodel.MainViewModel
import kotlinx.coroutines.launch

lateinit var navHostController: NavHostController
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SetupNav(navController: NavHostController){

    var mainViewModel: MainViewModel = viewModel()
    var groups = mainViewModel.allGroups.observeAsState()
    var scope = rememberCoroutineScope()
    if(!mainViewModel.anyUser()){
        Log.i("USUARIOS", "NO HAY USUARIOS")
        scope.launch {
            mainViewModel.createSampleUsers()
        }

    }
    if(mainViewModel.anyGroup()){
        groups.value?.let {
            groups.value!!.forEach {
                Log.i("GRUPOS", it.group.name)
                if(!it.users.isEmpty()){
                    it.users.forEach {
                        Log.i("GRUPOS USUARIO", it.name)
                    }
                }

            }
        }
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
        composable(route = Screen.ViewConversationGroup.route){
            ViewConversationGroup()
        }
        composable(route = Screen.ViewProfile.route){
            ProfileView()
        }
        composable(route = Screen.GroupProfile.route){
            GroupProfile()
        }
        composable(route = Screen.CreateUser.route){
            createUser()
        }
        composable(route = Screen.CreateGroup.route){
            createGroup()
        }
        composable(route = Screen.SelectUsers.route){
            selectUsers()
        }
        composable(route = Screen.AddMembersToGroup.route){
            addMembers()
        }


    }
}