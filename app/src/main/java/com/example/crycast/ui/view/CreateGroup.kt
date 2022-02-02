package com.example.crycast.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.model.User
import com.example.crycast.ui.Screen
import com.example.crycast.viewmodel.MainViewModel
import kotlinx.coroutines.launch

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
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Introduce el nombre del grupo")
            TextField(value = name, onValueChange = {
                name = it
            }, modifier = Modifier.fillMaxWidth())
            Text("Introduce la descripción del grupo")
            TextField(value = description, onValueChange = {
                description = it
            }, modifier = Modifier.fillMaxWidth())
            Text(text = infoText)

            IconButton(onClick = {
                if(!name.isEmpty() || !description.isEmpty()){
                    navHostController.navigate(Screen.SelectUsers.route)
                } else{
                    infoText = "Algún campo no es válido"
                }

            }) {
                Text("Siguiente")
            }
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