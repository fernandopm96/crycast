package com.example.crycast.repository

import com.example.crycast.dao.PrivateMessageDao
import com.example.crycast.model.PrivateMessage

class PrivateMessageRepository (private val messageDao: PrivateMessageDao){

    suspend fun addMessage(message: PrivateMessage){
        messageDao.insert(message)
    }
}