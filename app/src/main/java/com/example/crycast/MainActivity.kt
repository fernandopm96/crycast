package com.example.crycast

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.crycast.repository.UserRepository
import com.example.crycast.ui.theme.CrycastTheme
import com.example.crycast.ui.view.SetupNav
import com.example.crycast.viewmodel.ThemeViewModel
import com.example.crycast.viewmodel.ViewModel
import kotlinx.coroutines.runBlocking

lateinit var repository: UserRepository


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val themeViewModel: ThemeViewModel by viewModels()
        val viewModel: ViewModel by viewModels()

        setContent {

            CrycastTheme () {
                val navController = rememberNavController()
                SetupNav(navController = navController)
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

}