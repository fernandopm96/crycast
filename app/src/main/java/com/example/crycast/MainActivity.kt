package com.example.crycast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.example.crycast.model.DataStoreManager
import com.example.crycast.repository.UserRepository
import com.example.crycast.ui.theme.CrycastTheme
import com.example.crycast.ui.view.SetupNav
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.hildan.krossbow.stomp.config.StompConfig
import org.hildan.krossbow.stomp.sendText
import org.hildan.krossbow.stomp.stomp
import org.hildan.krossbow.websocket.okhttp.OkHttpWebSocketClient
import java.lang.Exception
import java.time.Duration
import java.util.prefs.Preferences

lateinit var repository: UserRepository

//val Context.dataStore: DataStore<Preferences> //= preferencesDataStore("crycast_stor")

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

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