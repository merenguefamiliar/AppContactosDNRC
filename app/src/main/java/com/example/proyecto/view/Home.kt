package com.example.gestortareasdnt.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyecto.model.Contacto
import com.example.proyecto.view.ComicSans
import com.example.proyecto.view.Screens
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaHome(navController: NavHostController) {
    val lista = CrearLista()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Contactos", color = Color.White, fontFamily = ComicSans) },
                colors = TopAppBarDefaults.topAppBarColors(Color.Red)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}, containerColor = Color.Red
            )
            { Icon(imageVector = Icons.Filled.Add, "Importar", tint = Color.White) }
        }
    ) { paddingValues ->
        Column(Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(15.dp, 15.dp)) {
            // MostrarLista(lista)
            MostrarLista3(lista, navController)
        }
    }
}

fun CrearLista(): List<Contacto> {
    val lista = listOf<Contacto>(
        Contacto("W.D. Gaster", 666, "https://i.imgur.com/NsdRQil.png", "SOY MUY MUY INTERESANTE"),
        Contacto(
            "Susie Gaster",
            66,
            "https://i.imgur.com/AWD9vvg.png",
            "oh mi nombre completo? es susie gaster"
        ),
        Contacto("Sans Gaster", 6, "https://i.imgur.com/2eyUnYA.png", "estoy pasando un mal rato")
    )

    return lista
}

@Composable
fun MostrarLista3(lista: List<Contacto>, navController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(lista) { contacto ->

            val nombre = URLEncoder.encode(contacto.nombre, StandardCharsets.UTF_8.toString())
            val urlImagen = URLEncoder.encode(contacto.urlImagen, StandardCharsets.UTF_8.toString())
            val bio = URLEncoder.encode(contacto.bio, StandardCharsets.UTF_8.toString())

            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = contacto.urlImagen,
                    contentDescription = contacto.nombre,
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            navController.navigate(
                                "${Screens.DetailScreen.route}/$nombre/${contacto.edad}/$urlImagen/$bio"
                            )
                        }
                )
                Text(contacto.nombre, textAlign = TextAlign.Center)
            }
        }
    }
}
