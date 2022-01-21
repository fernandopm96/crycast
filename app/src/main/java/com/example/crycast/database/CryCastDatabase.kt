package com.example.crycast.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crycast.model.User
import com.example.crycast.dao.UserDao
import kotlinx.coroutines.CoroutineScope

@Database(entities = [User::class], version = 1)
abstract class CryCastDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

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