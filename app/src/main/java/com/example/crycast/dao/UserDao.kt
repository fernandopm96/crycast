package com.example.crycast.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crycast.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM usuarios")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM usuarios WHERE id = :id")
    fun getUserById(id: Int) : User?

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM usuarios")
    suspend fun deleteAllUsers()

}