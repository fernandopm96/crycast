package com.example.crycast.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel(){

    private val _theme:MutableLiveData<String> = MutableLiveData<String>("Auto")
    val theme: LiveData<String> = _theme
    private val _darkTheme:MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)
    val darkTheme: LiveData<Boolean> = _darkTheme


    fun themeChange() {
        _darkTheme.value = !(_darkTheme.value)!!
        Log.i("DARKTHEME", darkTheme.value.toString())
    }
    /*
        Log.i("TEMA", "Tema cambiado" + theme.value)
        _theme.value = "Dark"/*
        when(newTheme){
            "Auto" -> theme.value = "Light"
            "Light" -> theme.value = "Dark"
            "Dark" -> theme.value = "Auto"
        }*/*/

}