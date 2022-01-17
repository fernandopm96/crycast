package com.example.crycast

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crycast.ui.theme.CrycastTheme
import com.example.crycast.ui.view.GetMainScaffold
import com.example.crycast.ui.view.MenuSuperiorPrincipal
import com.example.crycast.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: ThemeViewModel by viewModels()

        super.onCreate(savedInstanceState)

        setContent {
         //   val darkTheme=viewModel.darkTheme.observeAsState(initial = true)
            CrycastTheme () {
                GetMainScaffold()

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
    /*
    CrycastTheme(viewModel) {
        GetMainScaffold()
    }*/
}