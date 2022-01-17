package com.example.crycast.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//Provisional
val conversaciones = listOf<String>(
    "Hola",
    "Adiós",
    "asdflkja",
    "aaaaasdlk",
    "asdflkjañs",
)
@Composable
fun Conversaciones(){
    Column(modifier = Modifier.fillMaxHeight()){
        conversaciones.forEach {
            Conversacion(it)
            Divider()
        }
    }
}