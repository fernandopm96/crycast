package com.example.crycast.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.crycast.viewmodel.ViewModel


@Composable
fun Conversaciones(){
    val viewModel: ViewModel = viewModel()
    val users by viewModel.allUsers.observeAsState()

    Column(modifier = Modifier.fillMaxHeight()){


        users?.let { usersList ->

            usersList.forEach{
                Conversacion(it.user)
                Divider()
            }
        }
    }
}