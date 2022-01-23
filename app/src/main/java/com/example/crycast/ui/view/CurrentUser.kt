package com.example.crycast.ui.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.model.User
import com.example.crycast.ui.Screen
import com.example.crycast.viewmodel.ViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun currentUser(){
    var viewModel: ViewModel = viewModel()
    var scope = rememberCoroutineScope()
    val users by viewModel.allUsers.observeAsState()
    lateinit var user: User
    Scaffold(
        topBar = { TopBarCurrentUser()}
    ) {

        Column(

        ) {
            if(!viewModel.anyUser()) {
                Text("NO HAY USUARIOS")
                var initialUser = User(0, "fernando@mail.com", "Fernando", null)
                viewModel.currentUser = initialUser
                user = initialUser
                scope.launch {
                    viewModel.addUser(initialUser)
                }
            } else {
                users!!.forEach {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .weight(0.4f)
                            .clickable {
                                viewModel.currentUser = it.user
                                user = it.user
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically

                    ){
                        Text(it.user.name)
                    }

                }
            }
            FloatingActionButton(onClick = {
                if(user != null){
                    viewModel.currentUser = user
                    navHostController.navigate(Screen.Splash.route)
                }

            }) {

            }
        }
    }

}


@Composable
fun TopBarCurrentUser() {

    TopAppBar(

        title = { Text("Perfil") },
        /*navigationIcon = {
            IconButton(onClick = {
                navHostController.popBackStack()
            })
            {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }*/
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.height(60.dp)
    )
}
   /*     actions = {
            IconButton(onClick = {
                navHostController.navigate(Screen.Splash.route)
            }) {
                Icon(imageVector = Icons.Filled.Done, contentDescription = "Save profile")
            }

        }*/
