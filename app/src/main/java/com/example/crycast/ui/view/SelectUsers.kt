package com.example.crycast.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.R
import com.example.crycast.model.User
import com.example.crycast.ui.Screen
import com.example.crycast.viewmodel.MainViewModel

@Composable
fun selectUsers(){
    Scaffold(
        topBar = { topBarSelectUsers() },
        content = { usersToAdd() }
    )
}

@Composable
fun topBarSelectUsers(){
    TopAppBar(

        title = { Text("Selecciona los usuarios ")},
        navigationIcon = {
            IconButton(onClick = {
                navHostController.popBackStack()
            })
            {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.height(60.dp))

}

@Composable
fun usersToAdd(){
    val mainViewModel: MainViewModel = viewModel()
    val users by mainViewModel.allUsers.observeAsState()

    Column(modifier = Modifier.fillMaxHeight()){

        users?.let { usersList ->

            usersList.forEach{
                userItem(user = it)
                Divider()

            }
        }
    }
}
@Composable
fun userItem(user: User){

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .clickable {

        }) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Foto perfil",
                modifier = Modifier
                    .padding(10.dp)
                    .height(50.dp)
                    .width(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillWidth,

                )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.8f)) {
            Text(user.name, fontSize = (16.sp), modifier = Modifier.absolutePadding(10.dp,15.dp,0.dp, 0.dp))
        }

    }

}