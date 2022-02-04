package com.example.crycast.repository

import androidx.lifecycle.LiveData

import com.example.crycast.dao.UserDao
import com.example.crycast.model.User
class UserRepository(private val userDao: UserDao) {

   fun anyUser(): Boolean{
        if(userDao.anyUsers().isEmpty()){
            return false
        }
        return true
    }
    fun mailExists(mail: String): Boolean {
       var user = userDao.mailExists(mail)
        if(user == null){
            return false
        }
        return true
    }

    fun login(mail: String, password: String):User? {
        return userDao.login(mail, password)
    }
    fun insertMany(users: List<User>){
        userDao.insertMany(users)
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