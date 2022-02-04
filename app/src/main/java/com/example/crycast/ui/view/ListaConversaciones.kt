package com.example.crycast.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.example.crycast.model.Group
import com.example.crycast.model.GroupWithUsers
import com.example.crycast.model.User
import com.example.crycast.ui.Screen
import com.example.crycast.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout


@Composable
fun Conversaciones(){
    var tabIndex by remember { mutableStateOf(0) }
    val tabData = listOf(
        "USUARIOS",
        "GRUPOS",
    )
    Column() {
        TabRow(selectedTabIndex = tabIndex) {
            tabData.forEachIndexed { index, text ->
                Tab(selected = tabIndex == index, onClick = {
                    tabIndex = index

                }, text = {
                    Text(text = text)
                })
            }
        }
        if(tabIndex == 0){
            PrivateConversations()
        } else {
            GroupConversations()
        }
    }

}

@Composable
fun GroupConversations(){
    val mainViewModel: MainViewModel = viewModel()
    val groups by mainViewModel.allGroups.observeAsState()

    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier.heightIn(0.dp)
    ) {
        groups?.let {
            items(groups!!){
                ConversationGroup(it)
            }
        }
    }
}

@Composable
fun PrivateConversations(){
    val mainViewModel: MainViewModel = viewModel()
    val users by mainViewModel.allUsers.observeAsState()

    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        state = rememberLazyListState()
    ){
        users?.let {
            items(users!!){
                Conversacion(user = it)
            }
        }
    }
}

@Composable
fun Conversacion(user: User){

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .clickable {
            setDestination(user)
            navHostController.navigate(Screen.ViewConversation.route)
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
                .weight(0.6f)) {
            Text(user.name, fontSize = (16.sp), modifier = Modifier.absolutePadding(10.dp,15.dp,0.dp, 0.dp))
            Text("últ. mensaje", fontSize = (13.sp), modifier = Modifier.absolutePadding(10.dp,0.dp,0.dp, 15.dp))
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)) {
            Text("12:45",fontSize = (12.sp), modifier = Modifier.padding(5.dp))
        }

    }

}

@Composable
fun ConversationGroup(group: GroupWithUsers){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .clickable {
            currentGroup = group
            navHostController.navigate(Screen.ViewConversationGroup.route)
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
                .weight(0.6f)) {
            Text(group.group.name, fontSize = (16.sp), modifier = Modifier.absolutePadding(10.dp,15.dp,0.dp, 0.dp))
            Text("últ. mensaje", fontSize = (13.sp), modifier = Modifier.absolutePadding(10.dp,0.dp,0.dp, 15.dp))
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)) {
            Text("12:45",fontSize = (12.sp), modifier = Modifier.padding(5.dp))
        }

    }
}