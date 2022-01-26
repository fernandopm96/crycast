package com.example.crycast.ui.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.Credentials
import com.example.crycast.R
import com.example.crycast.ui.Screen
import com.example.crycast.ui.theme.*
import com.example.crycast.viewmodel.MainViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Composable
fun LoginScreen() {

    val mainViewModel: MainViewModel = viewModel()

    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val name = remember { mutableStateOf(TextFieldValue()) }
    val nameErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayMessages)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(R.drawable.crycast_logo),
            contentDescription = "Logo"
        )

        Spacer(Modifier.size(16.dp))
        OutlinedTextField(
            value = name.value,
            onValueChange = {
                if (nameErrorState.value) {
                    nameErrorState.value = false
                }
                name.value = it
            },
            isError = nameErrorState.value,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Introduce el nombre*", color = Color.Black)
            },
        )
        if (nameErrorState.value) {
            Text(text = "Obligatorio", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))
        val passwordVisibility = remember { mutableStateOf(true) }
        OutlinedTextField(
            value = password.value,
            onValueChange = {
                if (passwordErrorState.value) {
                    passwordErrorState.value = false
                }
                password.value = it
            },
         //   modifier = Modifier.background(Color.White),
            isError = passwordErrorState.value,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Introduce la contraseña*", color = Color.Black)
            },

            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {

                    Icon(
                        imageVector = if (passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,// as ImageVector,
                        contentDescription = "visibility",
                        tint = Color.Black
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None,

        )
        if (passwordErrorState.value) {
            Text(text = "Obligatorio", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){

            Button(
                onClick = {
                    when {
                        name.value.text.isEmpty() -> {
                            nameErrorState.value = true
                        }
                        password.value.text.isEmpty() -> {
                            passwordErrorState.value = true
                        }
                        else -> {
                            passwordErrorState.value = false
                            nameErrorState.value = false
                            Toast.makeText(
                                context,
                                "Login correcto",
                                Toast.LENGTH_SHORT
                            ).show()
                            var credentials = Credentials(name.value.text, password.value.text)
                            scope.async {
                                mainViewModel.login(credentials)
                            }
                            navHostController.navigate(Screen.Splash.route);
                        }
                    }

                },
                content = {
                    Text(text = "Login", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(3.dp))
                },
                modifier = Modifier

                    .background(PrimaryDark, shape = RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryDark)
            )
        }
    }
}
       // Spacer(Modifier.size(16.dp))
    /*    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            TextButton(onClick = {

            }) {
                Text(text = "Registro", color = Color.Red)
            }
        }
    }
}*/