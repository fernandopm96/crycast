package com.example.crycast.repository

import androidx.lifecycle.LiveData

import com.example.crycast.dao.UserDao
import com.example.crycast.model.User

class UserRepository(private val userDao: UserDao) {

    val getAllUsers: LiveData<List<User>> = userDao.getAllUsers()

    fun getUserById(id: Int): User? {
        return userDao.getUserById(id)
    }

    suspend fun addUser(user: User){
        userDao.insert(user)
    }

    suspend fun updateUser(user: User){
        userDao.update(user)
    }

    suspend fun deleteUser(user: User){
        userDao.delete(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }

}