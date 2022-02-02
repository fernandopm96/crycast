package com.example.crycast.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.model.User
import com.example.crycast.viewmodel.MainViewModel
import kotlinx.coroutines.launch

// Provisional para añadir usuarios a la base de datos, y visualizarlos en la interfaz


@Composable
fun crearUsuario(){
    val scope = rememberCoroutineScope()
    var mainViewModel: MainViewModel = viewModel()
    var idText by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var infoText by remember { mutableStateOf("") }
    Scaffold(
        topBar = { TopBarCreateUser() }
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            TextField(value = name, onValueChange = {
                name = it
            }, modifier = Modifier.fillMaxWidth())
            TextField(value = email, onValueChange = {
                email = it
            }, modifier = Modifier.fillMaxWidth())
            Text(text = infoText)

            IconButton(onClick = {
                if(!name.isEmpty() || !email.isEmpty()){
                    if(!mainViewModel.mailExists(email)){
                        var user: User = User(0, email, name, name, null)
                        scope.launch {
                            mainViewModel.addUser(user)
                            infoText = "Usuario añadido"
                        }
                        idText = ""
                        name = ""
                        email = ""
                    }
                    else {
                        infoText = "El email introducido ya pertenece a algún usuario."
                    }
                }


                else{
                    infoText = "Algún campo no es válido"
                }

            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }
}

@Composable
fun TopBarCreateUser(){

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