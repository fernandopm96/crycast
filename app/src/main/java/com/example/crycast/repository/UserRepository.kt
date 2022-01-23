package com.example.crycast.repository

import androidx.lifecycle.LiveData

import com.example.crycast.dao.UserDao
import com.example.crycast.model.User
import com.example.crycast.model.UserWithMessages

class UserRepository(private val userDao: UserDao) {

    val getAllUsers: LiveData<List<UserWithMessages>> = userDao.getAllUsers()

    fun getUserById(id: Int): UserWithMessages? {
        return userDao.getUserById(id)
    }
    fun getUserByName(name: String): UserWithMessages? {
        return userDao.getUserByName(name)
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