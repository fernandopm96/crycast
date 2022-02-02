package com.example.crycast.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.crycast.dto.Credentials
import com.example.crycast.dao.PrivateMessageDao
import com.example.crycast.dao.UserDao
import com.example.crycast.database.CryCastDatabase
import com.example.crycast.model.DataStoreManager
import com.example.crycast.model.PrivateMessage
import com.example.crycast.model.User
import com.example.crycast.repository.PrivateMessageRepository
import com.example.crycast.repository.UserRepository
import com.example.crycast.services.CrycastApiService
import com.example.crycast.services.UsersApiService
import com.example.crycast.ui.view.currentUser
import com.example.crycast.ui.view.destinationUser
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*

class MainViewModel (application: Application) : AndroidViewModel(application) {


    val sampleData: List<User> = listOf(
        User(0, "sandra@mail.com","Sandra", "Sandra", null),
        User(0, "fernando@mail.com","Fernando", "Fernando", null),
        User(0, "jose@mail.com","Jose", "Jose", null)
    )

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
    fun mailExists(mail:String): Boolean{
        if(userRepository.mailExists(mail)){
            return true
        }
        return false
    }
    fun login(mail: String, password: String): Boolean{
        var user = userRepository.login(mail, password)
        if(user != null){
            currentUser = user
            return true
        }
        return false
    }

    suspend fun createSampleUsers() {
        sampleData.forEach {
            addUser(it)
        }
    }
}

/*

 // API
    val usersApiService: UsersApiService = UsersApiService.getInstance()
    val crycastApiService: CrycastApiService = CrycastApiService.getInstance()
    var dataStoreManager: DataStoreManager = DataStoreManager(this.getApplication())

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
            var users = usersApiService.getUsers()
            if(!users.isSuccessful){
                Log.i("ok","ERROR, VIENE VACÍO")
            } else {

                users.body()?.forEach {
                    Log.i("ok",it.name)
                }
            }
        }

    }

    suspend fun update(){

        var users = crycastApiService.getUsersPrueba(dataStore.dataStoreLastUpdate.first()!!)
        if(users.isSuccessful){
            userDao.insertMany(users.body()!!)
        }
    }
}
    fun login(credentials: Credentials){

        CoroutineScope(Dispatchers.IO).launch {
            var response = usersApiService.login(credentials)
            if(response.isSuccessful){
                dataStore.savePrincipalUserId(response.body()!!.id)
                dataStore.savePrincipalUserMail(response.body()!!.mail)
                dataStore.savePrincipalUserName(response.body()!!.name)
                currentUser = response.body()!!
                update()
                Log.i("ok", response.body()!!.name)
            } else {
                Log.i("ok", "ERROR EN EL POST")
            }
        }
    }

    suspend fun setPrincipalUser(userId: String){
        dataStoreManager.savePrincipalUserName(userId)
    }

    suspend fun updateDataUser(lastUpdate: String){
        dataStoreManager.setLastUpdate(lastUpdate)
    }
*/

