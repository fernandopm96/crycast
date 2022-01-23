package com.example.crycast.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.crycast.dao.PrivateMessageDao
import com.example.crycast.dao.UserDao
import com.example.crycast.database.CryCastDatabase
import com.example.crycast.model.PrivateMessage
import com.example.crycast.model.User
import com.example.crycast.repository.PrivateMessageRepository
import com.example.crycast.repository.UserRepository
import com.example.crycast.ui.view.currentUser
import com.example.crycast.ui.view.destinationUser

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


}