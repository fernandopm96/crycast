package com.example.crycast.ui.view

import android.window.SplashScreenView
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.crycast.R
import com.example.crycast.ui.Screen
import com.example.crycast.ui.theme.PrimaryDark
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController){
    var startAnimation by remember { mutableStateOf(false)}
    var alphaAnimation = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4000)
        navController.navigate(Screen.Scaffold.route)
    }
    Splash(alphaAnimation.value)
}

@Composable
fun Splash(alpha: Float = 1f){
    Box(modifier = Modifier
        .background(if (isSystemInDarkTheme()) PrimaryDark else Color.White)
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(280.dp)
                .alpha(alpha),
            painter = painterResource(R.drawable.crycast_logo),
            contentDescription = "Logo"
        )
    }
}
@Composable
@Preview
fun SplashScreenView(){
    Splash()
}