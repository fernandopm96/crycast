package com.example.crycast.ui.view

import android.graphics.drawable.Icon
import android.text.Layout
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Menú superior de la pantalla principal de la aplicación.

@Composable
fun MenuSuperiorPrincipal(){
    TopAppBar(
        title = { Text(text = "CryCast")},
        navigationIcon = {
            IconButton(onClick = {

            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")
            }
        },
        backgroundColor = Color(0xFFC0E8D5),

    )

}