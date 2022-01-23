package com.example.crycast.ui.view

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.crycast.R
import com.example.crycast.model.User
import com.example.crycast.viewmodel.ThemeViewModel
import com.example.crycast.viewmodel.ViewModel
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
