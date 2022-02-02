package com.example.crycast.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.crycast.model.User
import com.example.crycast.model.UserWithMessages

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>

    @Transaction
    @Query("SELECT * FROM User u WHERE u.id != :id")
    fun getUsers(id: Int): LiveData<List<User>>

    @Transaction
    @Query("SELECT * FROM User WHERE id = :id")
    fun getUserById(id: Int) : UserWithMessages?

    @Transaction
    @Query("SELECT * FROM User WHERE name = :name")
    fun getUserByName(name: String) : UserWithMessages?

    @Query("SELECT * FROM User u WHERE u.mail = :mail")
    fun mailExists(mail: String): User?

    @Query("SELECT * FROM User u WHERE u.mail = :mail AND u.password = :password")
    fun login(mail: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(users : List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)
    @Delete
    suspend fun delete(user: User)
    @Query("DELETE FROM User")
    suspend fun deleteAllUsers()

}