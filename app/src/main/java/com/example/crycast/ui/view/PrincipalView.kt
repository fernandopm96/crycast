package com.example.crycast.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.R
import com.example.crycast.model.User
import com.example.crycast.ui.Screen
import com.example.crycast.viewmodel.DataStoreViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

var currentUser: User = User(0, "fernando@mail.com", "Fernando", "Fernando",null)
var destinationUser: User = User(0, "fernando@mail.com", "Fernando", "Fernando",null)

@Composable
fun GetMainScaffold(){

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(DrawerValue.Closed)
    )

    val activity = LocalContext.current as Activity

    BackHandler() {
        if(scaffoldState.drawerState.isClosed)
            activity.moveTaskToBack(true)
        else
            scope.launch{scaffoldState.drawerState.close()}
    }

    Scaffold(

        scaffoldState = scaffoldState,
        topBar = { MenuSuperiorPrincipal(scope, scaffoldState) },
        drawerContent = { DesplegableOpciones(scope, scaffoldState)},
        content ={
            Conversaciones()
        }
    )
}

// Menú superior de la pantalla principal de la aplicación.

@Composable
fun MenuSuperiorPrincipal(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
){
    var showMenu by remember{ mutableStateOf(false)}
    TopAppBar(
        title = { Text(text = "CryCast")},
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            IconButton(onClick = {
                showMenu = !showMenu
            }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Options")
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(onClick = {
                    navHostController.navigate(Screen.CreateUser.route)
                }) {
                    Text("Nuevo usuario")
                }
                DropdownMenuItem(onClick = {
                    navHostController.navigate(Screen.CreateGroup.route)
                }) {
                    Text("Nuevo grupo")
                }
            }
        }
    )


}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DesplegableOpciones(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    val dataStoreViewModel: DataStoreViewModel = viewModel()
    var userProfile = remember{ mutableStateOf(currentUser) }
    var darkMode = dataStoreViewModel.dataStoreTheme.collectAsState("").value

    val context = LocalContext.current
    val logout = remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.height(400.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .weight(0.3f),
            //    .height(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Volver")
            }
            IconButton(modifier = Modifier.absolutePadding(0.dp, 10.dp, 10.dp, 0.dp),
                onClick = {
                    scope.launch {
                        dataStoreViewModel.themeChange()
                    }

                }) {
                Icon(
                    painter = if(darkMode == "LIGHT") painterResource(id = R.drawable.ic_sun) else painterResource(R.drawable.moon),
                    contentDescription = "Change theme"
                )

            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .weight(0.8f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Foto perfil",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillWidth
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .weight(0.4f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(userProfile.value!!.name)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .weight(0.4f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(userProfile.value!!.mail)
        }


        Divider()

        Row(
            Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            Text("Cerrar sesión")
            IconButton(onClick = {
                navHostController.navigate(Screen.LoginScreen.route)
            }) {
                Icon(imageVector = Icons.Filled.Logout, contentDescription = "Cerrar sesión")
            }
        }


    }
}