package com.example.crycast.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.crycast.model.GroupWithUsers
import com.example.crycast.model.GroupsUsers

@Dao
interface GroupsUsersDao {

    @Insert
    fun joinGroupUser(join: GroupsUsers)

    @Transaction
    @Query("SELECT * FROM `Group`")
    fun getCourses(): List<GroupWithUsers>

}