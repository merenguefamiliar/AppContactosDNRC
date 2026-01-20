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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyecto.Contactos.Contacto
import com.example.proyecto.Screens.ComicSans
import com.example.proyecto.Screens.Screens
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaHome(navController: NavHostController)
{
    val lista = CrearLista()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Tareas", color = Color.White, fontFamily = ComicSans) },
                colors = TopAppBarDefaults.topAppBarColors(Color.Red)
            )
        }
    ) {
            paddingValues ->
        Column(Modifier.padding(paddingValues).fillMaxSize().padding(15.dp,15.dp)) {
            // MostrarLista(lista)
            MostrarLista2(lista, navController)
        }
    }
}

@Composable
fun Testeo()
{
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ){
        Column (
            modifier = Modifier
                .padding(10.dp)
                .background(color = Color.Red),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = "https://i.imgur.com/NsdRQil.png",
                "jscdksfjsdfjk",
                modifier = Modifier.size(100.dp).clickable(onClick = {})
            )
            Text("Susi")
        }

        Column (
            modifier = Modifier
                .padding(10.dp)
                .background(color = Color.Red),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = "https://i.imgur.com/NsdRQil.png",
                "jscdksfjsdfjk",
                modifier = Modifier.size(100.dp).clickable(onClick = {})
            )
            Text("Susi")
        }

        Column (
            modifier = Modifier
                .padding(10.dp)
                .background(color = Color.Red),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = "https://i.imgur.com/NsdRQil.png",
                "jscdksfjsdfjk",
                modifier = Modifier.size(100.dp).clickable(onClick = {})
            )
            Text("Susi")
        }


    }


}

fun CrearLista(): List<Contacto> {
    val lista = listOf<Contacto>(
        Contacto("W.D. Gaster",666, "https://i.imgur.com/NsdRQil.png", "SOY MUY MUY INTERESANTE"),
        Contacto("Susie Gaster",66, "https://i.imgur.com/AWD9vvg.png", "oh mi nombre completo? es susie gaster"),
        Contacto("Sans Gaster",6, "https://i.imgur.com/2eyUnYA.png", "estoy pasando un mal rato")
    )

    return lista
}

@Composable
fun MostrarLista(lista: List<Contacto>, navController: NavHostController)
{

    lista.forEach()
    {
        Row(Modifier.padding(25.dp).fillMaxWidth().height(80.dp),
            Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(it.nombre, fontWeight = FontWeight.Bold, color = Color.Red)
            AsyncImage(
                model = it.urlImagen,
                contentDescription = "cjscj",
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .clickable(onClick = {
                        navController.navigate("${Screens.DetailScreen.route}/${it.nombre}/${it.edad}/${it.urlImagen}/${it.bio}")
                    })
            )
        }
    }

}

@Composable
fun MostrarLista2(lista: List<Contacto>, navController: NavHostController)
{
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ){
        lista.forEach {
            val nombre = URLEncoder.encode(it.nombre, StandardCharsets.UTF_8.toString())
            val urlImagen = URLEncoder.encode(it.urlImagen, StandardCharsets.UTF_8.toString())
            val bio = URLEncoder.encode(it.bio, StandardCharsets.UTF_8.toString())


            Column(
                modifier = Modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = it.urlImagen,
                    "jscdksfjsdfjk",
                    modifier = Modifier.size(100.dp).clickable(onClick = {
                        navController.navigate(
                            "${Screens.DetailScreen.route}/$nombre/${it.edad}/$urlImagen/$bio"
                        )
                    })
                )
                Text(it.nombre)
            }
        }
    }
}