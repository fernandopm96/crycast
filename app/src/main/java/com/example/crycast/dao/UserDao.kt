package com.example.crycast.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crycast.model.User
import com.example.crycast.model.UserWithMessages

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<UserWithMessages>>

    @Transaction
    @Query("SELECT * FROM User WHERE id = :id")
    fun getUserById(id: Int) : UserWithMessages?
    @Transaction
    @Query("SELECT * FROM User WHERE name = :name")
    fun getUserByName(name: String) : UserWithMessages?


    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)
    @Delete
    suspend fun delete(user: User)
    @Query("DELETE FROM User")
    suspend fun deleteAllUsers()

}