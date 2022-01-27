package com.example.crycast.model

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.crycast.database.CryCastDatabase
import com.example.crycast.viewmodel.DataStoreViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.util.*

class DataStoreManager (val context: Context){

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("crycast_store")

    companion object{

        val USER = stringPreferencesKey("ID")
        val LAST_UPDATE = stringPreferencesKey("LAST_UPDATE")

    }

    val dataStoreLastUpdate: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[DataStoreViewModel.LAST_UPDATE] ?: Date.from(Instant.ofEpochMilli(0)).toString()
        }

    suspend fun setLastUpdate(date: String){
        context.dataStore.edit {
                preferences ->
            preferences[DataStoreViewModel.LAST_UPDATE] = date
        }
    }

    val dataStoreUser: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[DataStoreViewModel.USER] ?: "0"
        }

    suspend fun savePrincipalUser(idUser: String) {
        Log.i("viewmodel", "estableciendo usuario...")
        context.dataStore.edit {
                preferences ->
            preferences[DataStoreViewModel.USER] = idUser
        }
        Log.i("usuario", "usuario establecido" + idUser)
    }
}