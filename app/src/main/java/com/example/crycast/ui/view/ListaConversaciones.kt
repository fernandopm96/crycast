package com.example.crycast.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

//Provisional
val conversaciones = listOf<String>(
    "Hola",
    "Adiós",
    "asdflkja",
    "aaaaasdlk",
    "asdflkjañs",
)
@Composable
fun Conversaciones(navHostController: NavHostController){
    Column(modifier = Modifier.fillMaxHeight()){
        conversaciones.forEach {
            Conversacion(it, navHostController)
            Divider()
        }
    }
}