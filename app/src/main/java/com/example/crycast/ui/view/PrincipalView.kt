package com.example.crycast.ui.view

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.R
import com.example.crycast.ui.Screen
import com.example.crycast.viewmodel.ThemeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
                navHostController.navigate(Screen.CreateUser.route)
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
            }
        }
    )


}

@Composable
fun DesplegableOpciones(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    val themeViewModel: ThemeViewModel = viewModel()
    var userProfile = remember{ mutableStateOf(currentUser) }
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
                        themeViewModel.themeChange()
                        Log.i("BOTON", "CAMBIA TEMA")
                    }
                }) {
                Icon(
                    painter = painterResource(R.drawable.moon),
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

    }
}