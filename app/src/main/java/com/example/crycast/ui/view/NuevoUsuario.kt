package com.example.crycast.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.crycast.model.User
import com.example.crycast.viewmodel.ViewModel
import kotlinx.coroutines.launch

// Provisional para añadir usuarios a la base de datos, y visualizarlos en la interfaz

lateinit var controller: NavHostController
@Composable
fun crearUsuario(navHostController: NavHostController){
    controller = navHostController
    val scope = rememberCoroutineScope()
    var viewModel: ViewModel = viewModel()
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
            TextField(value = idText, onValueChange = {
                idText = it
            }, modifier = Modifier.fillMaxWidth())
            TextField(value = name, onValueChange = {
                name = it
            }, modifier = Modifier.fillMaxWidth())
            TextField(value = email, onValueChange = {
                email = it
            }, modifier = Modifier.fillMaxWidth())
            Text(text = infoText)

            IconButton(onClick = {
                if(!idText.isEmpty() || !name.isEmpty() || !email.isEmpty()){
                    var id: Int? = idText.toIntOrNull()
                    if(id != null){

                        var user: User = User(id!!, name, email, null)
                        scope.launch {
                            viewModel.addUser(user)
                            infoText = "Usuario añadido"
                        }
                        idText = ""
                        name = ""
                        email = ""
                    }
                    else{
                        infoText = "Algún campo no es válido"
                    }

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
                controller.popBackStack()
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