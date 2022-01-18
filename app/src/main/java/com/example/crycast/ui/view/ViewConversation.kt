package com.example.crycast.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crycast.R
import com.example.crycast.ui.Screen
import kotlinx.coroutines.launch

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
fun BottomBarConversation(){

}

@Composable
fun ViewConversation(navHostController: NavHostController){
    TopBarConversation(navHostController)
}
/*
@Composable
@Preview
fun TopBarPreview(){
    TopBarConversation()
}*/