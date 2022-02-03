package com.example.crycast.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.crycast.model.Group
import com.example.crycast.model.User
@Dao
interface GroupDao {

    @Query("SELECT * FROM `Group`")
    fun getAllGroups(): LiveData<List<Group>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(group: Group): Long
}