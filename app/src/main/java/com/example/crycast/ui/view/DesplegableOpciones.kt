package com.example.crycast.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import com.example.crycast.R
import com.example.crycast.viewmodel.ThemeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DesplegableOpciones(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    val themeViewModel: ThemeViewModel = viewModel()
    Column (
        modifier = Modifier.height(400.dp)
            ){
        Row(
            Modifier
                .fillMaxWidth()
                .weight(0.3f),
            //    .height(50.dp),
            horizontalArrangement =Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Volver")
            }
            IconButton(modifier = Modifier.absolutePadding(0.dp,10.dp, 10.dp, 0.dp),
                onClick = {
                    scope.launch {
                        themeViewModel.themeChange()
                        Log.i("BOTON","CAMBIA TEMA")
                    }
            }) {
                Icon(painter = painterResource(R.drawable.moon), contentDescription = "Change theme")

            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .weight(0.8f),
            horizontalArrangement =Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

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
                ) {
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
        Divider()


    }

}