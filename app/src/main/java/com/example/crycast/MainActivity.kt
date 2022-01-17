package com.example.crycast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.crycast.ui.theme.CrycastTheme
import com.example.crycast.ui.view.GetMainScaffold
import com.example.crycast.ui.view.MenuSuperiorPrincipal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrycastTheme {
                GetMainScaffold()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {


                }
            }

        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CrycastTheme {
        GetMainScaffold()
    }
}