package com.example.crycast.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.crycast.dao.PrivateMessageDao
import com.example.crycast.dao.UserDao
import com.example.crycast.database.CryCastDatabase
import com.example.crycast.model.PrivateMessage
import com.example.crycast.model.User
import com.example.crycast.model.UserWithMessages
import com.example.crycast.repository.PrivateMessageRepository
import com.example.crycast.repository.UserRepository

class ViewModel (application: Application) : AndroidViewModel(application){

    private var _destinationUser: MutableLiveData<User?> = MutableLiveData()
    val destinationUser: LiveData<User?> = _destinationUser
    // ROOM
    private val db: CryCastDatabase = CryCastDatabase.getInstance(application)
    var userDao: UserDao = db.userDao()
    var messageDao: PrivateMessageDao = db.messageDao()
    var userRepository: UserRepository = UserRepository(userDao)
    var messageRepository: PrivateMessageRepository = PrivateMessageRepository(messageDao)
   // Mensajes
    private var _messagesConversation: MutableLiveData<List<PrivateMessage>> = MutableLiveData(listOf<PrivateMessage>())
    var messagesConversation: LiveData<List<PrivateMessage>> = _messagesConversation
   // Usuarios
    var currentUser: User = User(0, "usuarioinicial", "Usuario inicial", null)
    internal val allUsers: LiveData<List<UserWithMessages>> = db.userDao().getAllUsers()

    val profileUser: LiveData<User> = MutableLiveData(currentUser)
    suspend fun addMessage(msg: PrivateMessage){
        messageRepository.addMessage(msg)
    }
    suspend fun addUser(user: User){
        userRepository.addUser(user)
    }

    fun anyUser(): Boolean{
        if(allUsers.value == null){
            return false
        }
        return true
    }
/*  fun getUserByName(name:String) : UserWithMessages?{
        return userRepository.getUserByName(name)
    }*/
/*
    fun setInitialProfile(){
        _profileUser.value = db.userDao().getUserByName("Fernando")!!.user
        currentUser = _profileUser.value
        Log.i("USUARIO ACTUAL", _profileUser.value!!.name)
    }*/

}