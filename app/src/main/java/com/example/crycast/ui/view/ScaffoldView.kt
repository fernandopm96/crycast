package com.example.crycast.ui.view

import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable

@Composable
fun GetMainScaffold(){
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open));
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MenuSuperiorPrincipal() }
    ) {

    }
}