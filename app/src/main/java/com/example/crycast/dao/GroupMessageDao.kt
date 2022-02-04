package com.example.crycast.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.crycast.model.GroupMessage
import com.example.crycast.model.MessageGroupWithUser

@Dao
interface GroupMessageDao {

    @Transaction
    @Query("SELECT * FROM GroupMessage gm WHERE gm.groupId = :groupId")
    fun messagesGroup(groupId: Long): LiveData<List<MessageGroupWithUser>>

    @Insert
    suspend fun insert(message: GroupMessage)


}