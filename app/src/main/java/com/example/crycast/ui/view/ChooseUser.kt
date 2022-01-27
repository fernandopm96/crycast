package com.example.crycast.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.model.User
import com.example.crycast.ui.Screen
import com.example.crycast.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun chooseUser(){
    var mainViewModel: MainViewModel = viewModel()
    var scope = rememberCoroutineScope()
    val users by mainViewModel.allUsers.observeAsState()
    var user: User? = null
    Scaffold(
        topBar = { TopBarCurrentUser()}
    ) {
        Column(

        ) {
            if(mainViewModel.anyUser()) {

                //Prueba
                    mainViewModel.currentTime()
                users?.let{
                    users!!.forEach {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .clickable {
                                    currentUser = it
                                    user = it
                                    navHostController.navigate(Screen.Splash.route)
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text(it.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                        Divider()
                    }
                }
            } else {

                var initialUser = User("0", "fernando@mail.com", "Fernando", null)
                currentUser = initialUser
                user = initialUser
                scope.launch {
                    mainViewModel.addUser(initialUser)
                }
                Row() {
                    Text("No hay usuarios. Se ha creado el usuario Fernando")
                }
                navHostController.navigate(Screen.Splash.route)
            }

        }
    }

}


@Composable
fun TopBarCurrentUser() {

    TopAppBar(

        title = { Text("Usuarios") },

        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.height(60.dp)
    )
}
