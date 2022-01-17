package com.example.crycast.ui.theme

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.crycast.viewmodel.ThemeViewModel

import androidx.lifecycle.viewmodel.compose.viewModel

private val DarkColorPalette = darkColors(
        primary = Purple200,
        primaryVariant = Purple700,
        secondary = Teal200
)

private val LightColorPalette = lightColors(
        primary = Purple500,
        primaryVariant = Teal200,
        secondary = Teal200

        /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CrycastTheme(
//    darkTheme: Boolean,
    content: @Composable() () -> Unit) {

    MaterialTheme(
            colors = if(isSystemInDarkTheme()) DarkColorPalette else LightColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}