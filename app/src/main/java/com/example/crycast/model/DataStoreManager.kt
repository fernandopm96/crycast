package com.example.crycast.model

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.crycast.viewmodel.DataStoreViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.util.*


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("crycast_store")

class DataStoreManager (val context: Context){

    private val dataStore = context.dataStore
    private val USER_ID = stringPreferencesKey("ID")
    private val MAIL = stringPreferencesKey("MAIL")
    private val NAME = stringPreferencesKey("NAME")
    private val LAST_UPDATE = stringPreferencesKey("LAST_UPDATE")


    val dataStoreUserId: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[DataStoreViewModel.USER] ?: "0"
        }
    val dataStoreUserMail: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[DataStoreViewModel.USER] ?: "0"
        }
    val dataStoreUserName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[DataStoreViewModel.USER] ?: "0"
        }
    val dataStoreLastUpdate: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[LAST_UPDATE] ?: Date.from(Instant.ofEpochMilli(0)).toString()
        }

    suspend fun savePrincipalUserId(idUser: String) {
        context.dataStore.edit {
                preferences ->
            preferences[USER_ID] = idUser
        }
    }

    suspend fun savePrincipalUserMail(mail: String) {
        context.dataStore.edit {
                preferences ->
            preferences[MAIL] =mail
        }
    }

    suspend fun savePrincipalUserName(name: String) {
        context.dataStore.edit {
                preferences ->
            preferences[NAME] = name
        }
    }
    suspend fun setLastUpdate(date: String){
        context.dataStore.edit {
                preferences ->
            preferences[DataStoreViewModel.LAST_UPDATE] = date
        }
    }


}