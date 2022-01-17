package com.example.crycast.ui.view

import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.R
import com.example.crycast.viewmodel.ThemeViewModel

@Composable
fun GetMainScaffold(){
    val themeViewModel: ThemeViewModel = viewModel()
    val navigationItems = listOf(
        "Perfil",
        "Opciones"
    )
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MenuSuperiorPrincipal(scope, scaffoldState) },
        drawerContent = { DesplegableOpciones(scope, scaffoldState)}
    ) {

    }
}