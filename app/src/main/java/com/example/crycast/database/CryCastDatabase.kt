package com.example.crycast.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crycast.dao.*
import com.example.crycast.model.*
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(User::class, PrivateMessage::class, Group::class, GroupMessage::class, GroupsUsers::class), version = 6)
abstract class CryCastDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun groupDao(): GroupDao
    abstract fun messageDao(): PrivateMessageDao
    abstract fun groupMessageDao(): GroupMessageDao
    abstract fun groupsUsersDao(): GroupsUsersDao
    // Objeto que sigue el patrón singleton

    companion object {
        private var INSTANCE: CryCastDatabase? = null

        // Devuelve la instancia de la base de datos. Si no existe, se crea.
        fun getInstance(
            context: Context,
        //    scope: CoroutineScope
        ): CryCastDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CryCastDatabase::class.java,
                        "crycast_database"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
            }
        }
    }
}