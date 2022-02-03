package com.example.crycast.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crycast.model.GroupWithUsers
import com.example.crycast.model.GroupsUsers
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface GroupsUsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun joinGroupUser(join: GroupsUsers)

    @Transaction
    @Query("SELECT * FROM `Group`")
    fun anyGroup(): List<GroupWithUsers>

    @Transaction
    @Query("SELECT * FROM `Group`")
    fun getGroupsWithUsers(): LiveData<List<GroupWithUsers>>

    @Delete
    suspend fun delete(groupUsers: GroupsUsers)
}