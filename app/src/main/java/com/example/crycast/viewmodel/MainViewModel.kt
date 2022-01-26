package com.example.crycast.viewmodel

import android.app.Application
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.crycast.Credentials
import com.example.crycast.dao.PrivateMessageDao
import com.example.crycast.dao.UserDao
import com.example.crycast.database.CryCastDatabase
import com.example.crycast.model.PrivateMessage
import com.example.crycast.model.User
import com.example.crycast.repository.PrivateMessageRepository
import com.example.crycast.repository.UserRepository
import com.example.crycast.ui.view.currentUser
import com.example.crycast.ui.view.destinationUser
import com.google.gson.Gson
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.hildan.krossbow.stomp.config.StompConfig
import org.hildan.krossbow.stomp.sendText
import org.hildan.krossbow.stomp.stomp
import org.hildan.krossbow.websocket.okhttp.OkHttpWebSocketClient
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*

class MainViewModel (application: Application) : AndroidViewModel(application){


    // ROOM
    private val db: CryCastDatabase = CryCastDatabase.getInstance(application)
    var userDao: UserDao = db.userDao()
    var messageDao: PrivateMessageDao = db.messageDao()
    var userRepository: UserRepository = UserRepository(userDao)
    var messageRepository: PrivateMessageRepository = PrivateMessageRepository(messageDao)
   // Usuarios

    var _allUsers = db.userDao().getUsers(currentUser.id)
    val allUsers: LiveData<List<User>> = _allUsers

   // Mensajes
   var messagesConversation: LiveData<List<PrivateMessage>> = messageDao.conversationMessages(currentUser.id, destinationUser.id)

    suspend fun addMessage(msg: PrivateMessage){
        messageRepository.addMessage(msg)
    }
    suspend fun addUser(user: User){
        userRepository.addUser(user)
    }

    fun anyUser(): Boolean{
        return userRepository.anyUser()
    }

    fun currentTime(){
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        Log.i("Fecha", currentDate)

    }
    //Recordar: Cambiar la version de kotlin, habilitar aaceso a red y parámetro de cleartext, importar subdependencias, uso de okHttpClient. No acepta conexiones a localhost!!!
    suspend fun conectaSocket(msg: String){
        Log.i("stomp","Corutina iniciada")
        try {
            val okHttpClient = OkHttpClient.Builder()
                .callTimeout(Duration.ofMinutes(1))
                .pingInterval(Duration.ofSeconds(10))
                .connectTimeout(Duration.ofSeconds(10))
                .build()

            val test = OkHttpWebSocketClient(okHttpClient)
            val connection = test.connect("ws://boiling-hollows-83192.herokuapp.com/chat")
            val config = StompConfig()
            val stomp = connection.stomp(config)

            stomp.sendText("/app/test",msg)

        }
        catch (e: Exception){e.printStackTrace()}

        Log.i("stomp","mensaje enviado")
    }


    suspend fun post(credentials: Credentials) {

        var client:OkHttpClient = OkHttpClient()
        var url = "https://boiling-hollows-83192.herokuapp.com/login"

        var json = Gson().toJson(credentials)

        val builder = FormBody.Builder()
        builder.add("credentials", json)
        val formBody = builder.build()
        val request = Request.Builder().url(url).post(formBody).build()

        val call = client.newCall(request)
        var response = call.execute()

        Log.i("Response", response.body.toString())
    }


}