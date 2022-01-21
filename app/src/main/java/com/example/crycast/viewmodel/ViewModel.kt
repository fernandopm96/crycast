package com.example.crycast.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.crycast.dao.UserDao
import com.example.crycast.database.CryCastDatabase
import com.example.crycast.model.User
import com.example.crycast.repository
import com.example.crycast.repository.UserRepository
import kotlinx.coroutines.runBlocking

class ViewModel (application: Application) : AndroidViewModel(application){
    // ROOM
    lateinit var userDao: UserDao
    lateinit var userRepository: UserRepository

   /* private var _users : MutableLiveData<List<User>> = MutableLiveData(listOf<User>())
    var users : LiveData<List<User>> = _users*/
    private val db: CryCastDatabase = CryCastDatabase.getInstance(application)
    internal val allUsers: LiveData<List<User>> = db.userDao().getAllUsers()
    private var _messages : MutableLiveData<List<String>> = MutableLiveData(listOf<String>())
    val messages: LiveData<List<String>> = _messages

    fun AddMessage(msg: String){
        Log.i("Mensaje en view model", msg)
        val newList:List<String> = _messages.value!!.plus(msg)
        _messages.value = newList
        messages.value?.forEach { Log.i("VIEWMODEL", it) }
    }
    suspend fun addUser(user: User){
        userDao = db.userDao()
        userRepository = UserRepository(userDao)
        userRepository.addUser(user)
    }

}