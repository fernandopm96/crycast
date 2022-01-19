package com.example.crycast.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ViewModel (application: Application) : AndroidViewModel(application){

    private var _messages : MutableLiveData<List<String>> = MutableLiveData(listOf<String>())
    val messages: LiveData<List<String>> = _messages

    fun AddMessage(msg: String){
        Log.i("Mensaje en view model", msg)
        val newList:List<String> = _messages.value!!.plus(msg)
        _messages.value = newList
        messages.value?.forEach { Log.i("VIEWMODEL", it) }
    }
}