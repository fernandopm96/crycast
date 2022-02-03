package com.example.crycast.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.model.Group
import com.example.crycast.model.GroupWithUsers
import com.example.crycast.ui.Screen
import com.example.crycast.ui.theme.PrimaryDark
import com.example.crycast.viewmodel.MainViewModel

lateinit var newGroup: Group
lateinit var newGroupWithUsers: GroupWithUsers

@Composable
fun createGroup(){
    val scope = rememberCoroutineScope()
    var mainViewModel: MainViewModel = viewModel()
    var idText by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var infoText by remember { mutableStateOf("") }
    Scaffold(
        topBar = { TopBarCreateUser() }
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Introduce el nombre del grupo")
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = name, onValueChange = {
                name = it
            }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(20.dp))
            Text("Introduce la descripción del grupo")
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = description, onValueChange = {
                description = it
            }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = infoText)
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if(!name.isEmpty() || !description.isEmpty()){
                    newGroup = Group(0, name, description)
                    navHostController.navigate(Screen.SelectUsers.route)
                } else {
                    infoText = "Algún campo no es válido"
                }

            }, modifier = Modifier
                .background(PrimaryDark, shape = RoundedCornerShape(20.dp)),
                content = {
                    Text(text = "Siguiente", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(3.dp))
                },
            )
        }
    }
}

@Composable
fun TopBarCreateGroup(){

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
        modifier = Modifier.height(60.dp))

}