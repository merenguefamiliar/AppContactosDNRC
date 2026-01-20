package com.example.proyecto.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalles(navController: NavHostController, nombre: String?, edad: Int?, urlImagen: String?, bio: String?) {

    val nombre = URLDecoder.decode(nombre ?: "", StandardCharsets.UTF_8.toString())
    val urlImagen = URLDecoder.decode(urlImagen ?: "", StandardCharsets.UTF_8.toString())
    val bio = URLDecoder.decode(bio ?: "", StandardCharsets.UTF_8.toString())
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft, "Volver",
                        tint = Color.White, modifier = Modifier.clickable(onClick = { navController.popBackStack()})
                    )
                },
                title = { Text("Detalles", color = Color.White, fontFamily = ComicSans) },
                colors = TopAppBarDefaults.topAppBarColors(Color.Red)
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(15.dp, 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = urlImagen,
                "Imagen de la persona",
                modifier = Modifier.size(300.dp)
            )
            Text("Nombre: $nombre")
            Text("Edad: $edad")
            Text("Biografía: $bio")
        }
    }
}