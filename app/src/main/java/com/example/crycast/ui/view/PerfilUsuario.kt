package com.example.crycast.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
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

lateinit var navHostController: NavHostController
@Composable
fun ProfileView(controller: NavHostController){
    navHostController = controller
    Scaffold(
        topBar = { TopBarProfileView()}
    )
    {
        Column() {

            Row (
                Modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                horizontalArrangement =Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Foto perfil",
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.FillWidth
                )
            }
            Row (
                Modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                horizontalArrangement =Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text("Nombre de usuario")
            }
            Row (
                Modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                horizontalArrangement =Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Email")
            }
        }

    }
}

@Composable
fun TopBarProfileView(){

    TopAppBar(

        title = {},
        navigationIcon = {
            IconButton(onClick = {
                navHostController.popBackStack()
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