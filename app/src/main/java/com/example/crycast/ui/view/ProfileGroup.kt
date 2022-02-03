package com.example.crycast.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.R
import com.example.crycast.ui.Screen
import com.example.crycast.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.stream.Collectors


@Composable
fun GroupProfile(){
    Scaffold(
        topBar = { TopBarProfileGroup() },
        floatingActionButton = { FloatingActionButton(onClick = {
            navHostController.navigate(Screen.AddMembersToGroup.route)
        }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Aceptar")
        }}
    )
    {
        Column {
            InfoPanel()
            Divider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Participantes",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Divider()
            Members()
        }
    }
}


@Composable
fun InfoPanel(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Foto perfil",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = if (currentGroup != null) currentGroup.group.name else "Grupo",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
            )
    }
}

@Composable
fun Members(){

    var mainViewModel: MainViewModel = viewModel()
    var users = mainViewModel.membersGroup.observeAsState()
    var scope = rememberCoroutineScope()

    if(mainViewModel.anyGroup()){
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            users.value?.let {
                users.value!!.forEach { user ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(10.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        scope.launch {
                                            mainViewModel.removeMember(user)
                                            navHostController.navigate(Screen.GroupProfile.route)
                                        }
                                    }
                                )
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "Foto perfil",
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.FillWidth
                        )
                        Text(
                            text = user.name,
                            fontSize = 16.sp,
                            modifier = Modifier.absolutePadding(50.dp, 0.dp, 0.dp, 0.dp)
                        )
                    }
                    Divider()
                }

            }

        }
    }

}


@Composable
fun TopBarProfileGroup(){

    TopAppBar(

        title = {},
        navigationIcon = {
            IconButton(onClick = {
                navHostController.navigate(Screen.ViewConversationGroup.route)
            })
            {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.height(60.dp),
        actions = {
            IconButton(onClick = {

            }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Setting")
            }

        }
    )

}

@Composable
fun addMembers(){
    var mainViewModel: MainViewModel = viewModel()
    var scope = rememberCoroutineScope()
    Scaffold(
        topBar = { topBarSelectUsers() },
        content = { otherMembers() },
        floatingActionButton = { FloatingActionButton(onClick = {
            if(selectedUsers.isEmpty()){
                Log.i("USUARIOS SELECCIONADOS", "NO HAY")
            } else {
                scope.launch {
                    mainViewModel.addUsersToGroup(currentGroup.group.groupId, selectedUsers)
                    selectedUsers = mutableListOf()
                }
            }
        }) {
            Icon(imageVector = Icons.Filled.Done, contentDescription = "Aceptar")
        }}
    )
}

@Composable
fun otherMembers(){
    val mainViewModel: MainViewModel = viewModel()
    val otherUsers by mainViewModel.notIncludedMembers.observeAsState()

    Column(modifier = Modifier.fillMaxHeight()){

        otherUsers?.let { usersList ->

            usersList.forEach{
                userItem(user = it)
                Divider()

            }
        }
    }
}

