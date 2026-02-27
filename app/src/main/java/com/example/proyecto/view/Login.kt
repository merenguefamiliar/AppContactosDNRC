package com.example.proyecto.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto.R

val ComicSans = FontFamily(Font(R.font.comic_sans_ms, FontWeight.Normal))

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLogin(navController: NavHostController) {

    val context = LocalContext.current // para el toast

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("login", color = Color.White, fontFamily = ComicSans) },
                colors = TopAppBarDefaults.topAppBarColors(Color.Red)
            )
        }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Introduzca sus credenciales\n", fontSize = 20.sp, fontFamily = ComicSans)
            var usuario by rememberSaveable { mutableStateOf("") }
            TextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            var contrasena by rememberSaveable { mutableStateOf("") }
            var visible by rememberSaveable { mutableStateOf(false) }

            TextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = if (visible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = {
                        visible = !visible
                    }) {
                        val icono = if (visible)
                            Icons.Filled.VisibilityOff
                        else
                            Icons.Filled.Visibility
                        Icon(imageVector = icono, contentDescription = "")
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                if (usuario.isNotBlank() && contrasena.isNotBlank()) {
                    navController.navigate(Screens.HomeScreen.route)
                } else {
                    Toast.makeText(context, "Falta algo por rellenar!!", Toast.LENGTH_SHORT).show()
                }
            }, colors = ButtonDefaults.buttonColors(Color.Red)) {
                Icon(imageVector = Icons.Filled.Done, "Iniciar sesión")
            }
        }
    }
}