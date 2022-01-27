package com.example.crycast.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.MainActivity
import com.example.crycast.dto.Credentials
import com.example.crycast.dao.PrivateMessageDao
import com.example.crycast.dao.UserDao
import com.example.crycast.database.CryCastDatabase
import com.example.crycast.model.DataStoreManager
import com.example.crycast.model.PrivateMessage
import com.example.crycast.model.User
import com.example.crycast.repository.PrivateMessageRepository
import com.example.crycast.repository.UserRepository
import com.example.crycast.services.ApiService
import com.example.crycast.ui.view.currentUser
import com.example.crycast.ui.view.destinationUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.hildan.krossbow.stomp.config.StompConfig
import org.hildan.krossbow.stomp.sendText
import org.hildan.krossbow.stomp.stomp
import org.hildan.krossbow.websocket.okhttp.OkHttpWebSocketClient
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*

class MainViewModel (application: Application) : AndroidViewModel(application) {


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
    var messagesConversation: LiveData<List<PrivateMessage>> =
        messageDao.conversationMessages(currentUser.id, destinationUser.id)

    // API
    val apiService: ApiService = ApiService.getInstance()


    var dataStoreManager: DataStoreManager = DataStoreManager(this.getApplication())

    suspend fun addMessage(msg: PrivateMessage) {
        messageRepository.addMessage(msg)
    }

    suspend fun addUser(user: User) {
        userRepository.addUser(user)
    }

    fun anyUser(): Boolean {
        return userRepository.anyUser()
    }

    fun currentTime() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        Log.i("Fecha", currentDate)

    }

    //Recordar: Cambiar la version de kotlin, habilitar aaceso a red y parámetro de cleartext, importar subdependencias, uso de okHttpClient. No acepta conexiones a localhost!!!
    suspend fun conectaSocket(msg: String) {
        Log.i("stomp", "Corutina iniciada")
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

            stomp.sendText("/app/privatemessage", msg)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        Log.i("stomp", "mensaje enviado")
    }

    fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            var users = apiService.getUsers()
            if(!users.isSuccessful){
                Log.i("ok","ERROR, VIENE VACÍO")
            } else {

                users.body()?.forEach {
                    Log.i("ok",it.name)
                }
            }
        }

    }

    fun login(credentials: Credentials){

        CoroutineScope(Dispatchers.IO).launch {
            var response = apiService.login(credentials)
            if(response.isSuccessful){
                Log.i("ok", response.body()!!.name)
            } else {
                Log.i("ok", "ERROR EN EL POST")
            }
        }
    }

    suspend fun setPrincipalUser(userId: String){
        dataStoreManager.savePrincipalUser(userId)
    }

    suspend fun updateDataUser(lastUpdate: String){
        dataStoreManager.setLastUpdate(lastUpdate)
    }

}

