package com.example.crycast.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.crycast.model.User
import com.example.crycast.model.UserWithMessages

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>>
    @Query("SELECT * FROM User")
    fun anyUsers(): List<User>

    @Query("SELECT * FROM User u WHERE u.userId IN (SELECT g.userId FROM GroupsUsers g WHERE g.groupId = :groupId)")
    fun membersGroup(groupId: Long): LiveData<List<User>>

    @Query("SELECT * FROM User u WHERE u.userId NOT IN (SELECT g.userId FROM GroupsUsers g WHERE g.groupId = :groupId)")
    fun getUsersNotIncludedInGroup(groupId: Long): LiveData<List<User>>

    @Transaction
    @Query("SELECT * FROM User u WHERE u.userId != :id")
    fun getUsers(id: Int): LiveData<List<User>>

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :id")
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