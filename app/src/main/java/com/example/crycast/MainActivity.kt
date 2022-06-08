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

    suspend fun conectaSocket(){
        Log.i("stomp","Corutina iniciada")
        try {
            val okHttpClient = OkHttpClient.Builder()
                .callTimeout(Duration.ofMinutes(1))
                .pingInterval(Duration.ofSeconds(10))
                .connectTimeout(Duration.ofSeconds(10))
                .build()

            val test = OkHttpWebSocketClient(okHttpClient)
            val connection = test.connect("ws://172.24.112.1:8080/chat")
            val config = StompConfig()
            //config.connectWithStompCommand = true
            //config.connectionTimeout = 15.seconds
            val stomp = connection.stomp(config)

//            val subscription = stomp.subscribeText("/topic/messages")

//            subscription.collect { msg ->
//                println("Received: $msg")
//            }

            stomp.sendText("/app/test","FUNCIONA YA")

            //val client = StompClient(OkHttpWebSocketClient())
            // session = client.connect("ws://127.0.0.1:8080/chat")
            //session.sendText("/topic/test","FUNCIONA YA")
        }
        catch (e:Exception){e.printStackTrace()}

        //session.sendText("/topic/test", "Basic text message")

//        mStompClient.topic("/topic/messages").subscribe { topicMessage: StompMessage ->
//            println(topicMessage.payload)
//        }


        //mStompClient.send("/app/test","prueba").subscribe()
        Log.i("stomp","mensaje enviado")
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        setContent {

            CrycastTheme () {
                runBlocking { conectaSocket() }
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