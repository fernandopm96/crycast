package com.example.crycast.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

enum class Theme {
    LIGHT,
    DARK
}
class DataStoreViewModel(application: Application) : AndroidViewModel(application){

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("darkMode")
        val THEME = stringPreferencesKey("THEME")
        val USER = stringPreferencesKey("ID")
    }
    // ID del usuario principal
    val dataStoreUser: Flow<String?> = application.applicationContext.dataStore.data
        .map { preferences ->
            preferences[USER] ?: "0"
        }

    suspend fun savePrincipalUser(idUser: String) {
        Log.i("viewmodel", "estableciendo usuario...")
        this.getApplication<Application>().applicationContext.dataStore.edit {
                preferences ->
            preferences[USER] = idUser
        }
        Log.i("usuario", "usuario establecido" + idUser)
    }



    // TEMA ELEGIDO
    val dataStoreTheme: Flow<String?> = application.applicationContext.dataStore.data
        .map { preferences ->
        preferences[THEME] ?: "LIGHT"
    }
    suspend fun themeChange() {
        Log.i("DATA STORE THEME", dataStoreTheme.first().toString())
        when(dataStoreTheme.first()){
            "LIGHT" ->  saveTheme(Theme.DARK)
            "DARK" -> saveTheme(Theme.LIGHT)
        }

    }


    //save email into datastore
    suspend fun saveTheme(theme: Theme) {
        Log.i("viewmodel suspend", "cambia tema")
        this.getApplication<Application>().applicationContext.dataStore.edit {
                preferences ->
            preferences[THEME] = theme.name
        }
    }
}
