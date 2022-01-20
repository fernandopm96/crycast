package com.example.crycast.ui.view

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.crycast.R
import com.example.crycast.ui.Screen
import com.example.crycast.ui.theme.GrayMessages
import com.example.crycast.viewmodel.ViewModel

@Composable
fun TopBarConversation(navHostController: NavHostController){

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
            modifier = Modifier.absolutePadding(20.dp)) {
                Text(text = "Godofredo")
                Text(text = "últ. hora: 20:45", fontSize = 12.sp)
            }
                },
        navigationIcon = {
             IconButton(onClick = {
                navHostController.popBackStack()
             })
             {
                 Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Setting")
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
fun CustomTextField(){
    var value by remember { mutableStateOf("") }
    val viewModel: ViewModel = viewModel()
    val messages by viewModel.messages.observeAsState()
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
                            Log.i("Mensaje", value)
                            viewModel.AddMessage(value)
                            value = ""
                        }

                    })
        }
    )
}



@Composable
fun ViewConversation(navHostController: NavHostController){
    val viewModel: ViewModel = viewModel()
    val messages by viewModel.messages.observeAsState()

    Scaffold(
        topBar = { TopBarConversation(navHostController = navHostController) },
        bottomBar = { CustomTextField() },
    ){
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.End
        ) {
            items(messages!!) {
                MessageBox(it)

            }
        }
    }
}



@Composable
fun MessageBox(msg: String){

  /*  var lines: MutableList<String> = mutableListOf()
    if(msg.length >25) {
        var inicio = 0
        var fin = 25
        while(msg.length > fin){
            lines.add(msg.substring(inicio, fin))
            inicio += 25
            fin += 25
        }
        if(msg.length > inicio){
            lines.add(msg.substring(inicio, msg.length))
        }

    }
    var message = ""
    lines.forEach {
        message += it + "\n"
    }*/
        var paddingLeftHora = 0
        Box(

            modifier = Modifier
                //.weight(0.8f)
                .widthIn(200.dp, 300.dp)
                .padding(10.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = msg,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                    //    .absolutePadding(10.dp, 5.dp, 5.dp, 5.dp)
                        .align(Alignment.Top)
                )
                Text(
                    text = "10:35",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    modifier = Modifier
                   //     .absolutePadding(50.dp, 20.dp, 5.dp, 0.dp)
                        .align(Alignment.Bottom)
                )
            }

        }






}