package com.example.crycast.ui.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crycast.dto.Credentials
import com.example.crycast.R
import com.example.crycast.model.User
import com.example.crycast.ui.Screen
import com.example.crycast.ui.theme.*
import com.example.crycast.viewmodel.MainViewModel
import kotlinx.coroutines.launch



@Composable
fun LoginScreen() {
    val mainViewModel: MainViewModel = viewModel()
    val mail = remember { mutableStateOf(TextFieldValue()) }
    val mailErrorState = remember { mutableStateOf(false) }
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
            value = mail.value,
            onValueChange = {
                if (mailErrorState.value) {
                    mailErrorState.value = false
                }
                mail.value = it
            },
            isError = mailErrorState.value,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Introduce tu email*", color = Color.Black)
            },
        )
        if (mailErrorState.value) {
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
            isError = passwordErrorState.value,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Introduce la contrase??a*", color = Color.Black)
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
                        mail.value.text.isEmpty() -> {
                            mailErrorState.value = true
                        }
                        password.value.text.isEmpty() -> {
                            passwordErrorState.value = true
                        }
                        else -> {
                            passwordErrorState.value = false
                            mailErrorState.value = false

                            if(!mailErrorState.value && !passwordErrorState.value){
                                if(mainViewModel.login(mail.value.text, password.value.text)){
                                    navHostController.navigate(Screen.Splash.route)
                                }
                                else {
                                    mailErrorState.value = true
                                    passwordErrorState.value = true
                                }

                            }


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
