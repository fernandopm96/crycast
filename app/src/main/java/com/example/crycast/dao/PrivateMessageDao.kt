package com.example.crycast.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.crycast.model.PrivateMessage

@Dao
interface PrivateMessageDao {

    @Transaction
    @Query("SELECT * FROM PrivateMessage WHERE idUser = :idUser AND idDestinationUser = :idDestinationUser")
    fun conversationMessages(idUser: Int, idDestinationUser: Int): LiveData<List<PrivateMessage>>

    @Insert
    suspend fun insert(message: PrivateMessage)


}