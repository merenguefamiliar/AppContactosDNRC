package com.example.proyecto.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto.R

val ComicSans = FontFamily(Font(R.font.comic_sans_ms, FontWeight.Normal))

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PantallaLogin(navController: NavHostController) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("login", color = Color.White, fontFamily = ComicSans)},
                colors = TopAppBarDefaults.topAppBarColors(Color.Red)
            )
        }
    )
    {
        paddingValues ->
        Column (modifier = Modifier
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
                label = { Text("Usuario")}
            )

            var contrasena by rememberSaveable { mutableStateOf("") }
            TextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") }
            )

            Button( onClick = {
                navController.navigate(Screens.HomeScreen.route)
            }, colors = ButtonDefaults.buttonColors(Color.Red)) {
                Icon(imageVector = Icons.Filled.Done, "Iniciar sesión")
            }
        }
    }
}