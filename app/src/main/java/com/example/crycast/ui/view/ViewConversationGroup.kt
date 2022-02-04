package com.example.crycast.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.R
import com.example.crycast.model.*
import com.example.crycast.ui.Screen
import com.example.crycast.ui.theme.*
import com.example.crycast.viewmodel.DataStoreViewModel
import com.example.crycast.viewmodel.MainViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

var currentGroup: GroupWithUsers = GroupWithUsers(Group(0, "", ""), mutableListOf<User>())

@Composable
fun TopBarConversationGroup(){
    TopAppBar(

        title = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Foto perfil",
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.FillWidth
                )
            }
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .absolutePadding(20.dp)
                    ) {

                Text(text =
                if(currentGroup != null){
                    currentGroup.group.name
                } else {
                    "Grupo"
                })

            }
        },
        navigationIcon = {
            IconButton(onClick = {
                navHostController.navigate(Screen.Scaffold.route)
            })
            {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Setting")
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.clickable { navHostController.navigate(Screen.GroupProfile.route) },
        actions = {
            IconButton(onClick = {

            }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Setting")
            }

        }
    )
}

// TextField para enviar mensaje
@Composable
fun CustomTextFieldGroup(){

    var value by remember { mutableStateOf("") }
    val mainViewModel: MainViewModel = viewModel()
    val scope = rememberCoroutineScope()

    TextField(
        value = value,
        onValueChange = {
            if(it.length == 25)
                it.plus("\n")
            value = it},
        maxLines = 5,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Enviar",
                modifier = Modifier
                    .absolutePadding(0.dp, 0.dp, 20.dp, 0.dp)
                    .clickable {
                        if (value != "") {
                            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                            val currentDate = sdf.format(Date())

                            var msg = GroupMessage(
                                0,
                                currentUser.userId,
                                currentGroup.group.groupId,
                                value,
                                currentDate
                            )
                            // JSON
                            scope.launch {
                                mainViewModel.addGroupMessage(msg)
                                Log.i("Mensaje registrado", value)
                            }
                            value = ""
                        }
                    })
        }
    )
}


@Composable
fun ViewConversationGroup(){
    var dataStoreViewModel: DataStoreViewModel = viewModel()

    val theme = dataStoreViewModel.dataStoreTheme.collectAsState("").value
    val mainViewModel: MainViewModel = viewModel()
    val messagesGroup by mainViewModel.messagesGroup.observeAsState()

    Scaffold(
        topBar = { TopBarConversationGroup() },
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                color = if (theme.equals("LIGHT")) Background else SecondaryLight
            )
        ){
            Column(modifier = Modifier.weight(0.8f)) {
                LazyColumn(
                    reverseLayout = true,
                    modifier = Modifier
                        .fillMaxSize(),
                    state = rememberLazyListState(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Top
                ) {
                    messagesGroup?.let {
                        items(messagesGroup!!.reversed()){
                            if(it.user.userId == currentUser.userId){
                                MessageBoxGroup(msg = it.groupMessage)
                            } else {
                                OtherMessageBoxGroup(msg = it)
                            }
                        }
                    }
                }
            }
            CustomTextFieldGroup()
        }


    }

}
@Composable
fun MessageBoxGroup(msg: GroupMessage){
    var dataStoreViewModel: DataStoreViewModel = viewModel()

    val theme = dataStoreViewModel.dataStoreTheme.collectAsState("").value

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.End)
                .widthIn(100.dp, 300.dp)
                .padding(5.dp)
                .background(
                    color = if (theme.equals("LIGHT")) PrimaryLight else PrimaryDarkVariant,
                    shape = RoundedCornerShape(10.dp)
                ),
        ) {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val sdf2 = SimpleDateFormat("hh:mm")
            val msgDate = Date(sdf.parse(msg.createDate).getTime())

            Text(
                text = msg.text,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier
                    .absolutePadding(15.dp, 5.dp, 20.dp, 5.dp)
                    .align(Alignment.TopStart)
            )
            Text(
                text = sdf2.format(msgDate),
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                modifier = Modifier
                    .absolutePadding(30.dp, 30.dp, 10.dp, 5.dp)
                    .align(Alignment.BottomEnd)
            )

        }
    }


}
@Composable
fun OtherMessageBoxGroup(msg: MessageGroupWithUser){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Start)
                .widthIn(100.dp, 300.dp)
                .padding(5.dp)
                .background(color = GrayMessages, shape = RoundedCornerShape(10.dp)),
        ) {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val sdf2 = SimpleDateFormat("hh:mm")
            val msgDate = Date(sdf.parse(msg.groupMessage.createDate).getTime())
            Column(){
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = msg.user.name,
                        color = Color.Red,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .absolutePadding(15.dp, 5.dp, 5.dp, 0.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = msg.groupMessage.text,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .absolutePadding(15.dp, 5.dp, 20.dp, 5.dp)
                    )
                    Text(
                        text = sdf2.format(msgDate),
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .absolutePadding(30.dp, 30.dp, 10.dp, 5.dp)
                    )

                }
            }




        }
    }


}