package com.example.crycast.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crycast.R
import com.example.crycast.ui.Screen

@Composable
fun Conversacion(msg: String, navHostController: NavHostController){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .clickable {
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
            Text("Nombre", fontSize = (16.sp), modifier = Modifier.absolutePadding(10.dp,15.dp,0.dp, 0.dp))
            Text(msg, fontSize = (13.sp), modifier = Modifier.absolutePadding(10.dp,0.dp,0.dp, 15.dp))
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