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

class ThemeViewModel(application: Application) : AndroidViewModel(application){

 /*   private val _theme:MutableLiveData<String> = MutableLiveData<String>("Auto")
    val theme: LiveData<String> = _theme
    private val _darkTheme:MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)
    val darkTheme: LiveData<Boolean> = _darkTheme*/
    companion object{
         private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("darkMode")
         val THEME = stringPreferencesKey("THEME")
    }


    val dataStoreTheme: Flow<String?> = application.applicationContext.dataStore.data
        .map { preferences ->
        preferences[THEME] ?: ""
    }

 //   val theme: MutableLiveData<String?> = MutableLiveData()


    suspend fun themeChange() {
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

enum class Theme {
    LIGHT,
    DARK
}