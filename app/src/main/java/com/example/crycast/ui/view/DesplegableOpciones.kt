package com.example.crycast.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    Column(){
        IconButton(onClick = {
            scope.launch {
                scaffoldState.drawerState.close()
            }
        }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Volver")
        }
        IconButton(onClick = {
            //themeViewModel.themeChange(themeViewModel.theme.toString())
        }) {
            Icon(painter = painterResource(R.drawable.moon), contentDescription = "Change theme")

        }
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Foto perfil",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text("Nombre de usuario")
        Text("Email")

    }

}