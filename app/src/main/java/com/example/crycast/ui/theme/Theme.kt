package com.example.crycast.ui.theme

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import com.example.crycast.viewmodel.ThemeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
        primary = PrimaryDark,
    primaryVariant = PrimaryLightVariant,
        onPrimary = Color.White,
        secondary = PrimaryLight,
    onSecondary = Color.White,
    background = SecondaryLight,
    surface = PrimaryDark,
    onSurface = Color.White
)
//primaryVariant = PrimaryDarkVariant,
private val LightColorPalette = lightColors(
        primary = PrimaryLight,
        primaryVariant = PrimaryLightVariant,
        secondary = Teal200,
        onSecondary = Color.Black,
        background = Color.White,
    surface =PrimaryLightVariant,
    //    surface = ,
)
        /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */

@Composable
fun CrycastTheme(
    content: @Composable() () -> Unit
) {

    var themeViewModel: ThemeViewModel = viewModel()

    val theme = themeViewModel.dataStoreTheme.collectAsState("").value
    Log.i("THEME", theme.toString())


    MaterialTheme(
            colors = if(theme.equals("LIGHT")) LightColorPalette else DarkColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}