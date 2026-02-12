package com.example.proyecto.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyecto.data.local.ContactEntity
import com.example.proyecto.viewmodel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaHome(viewModel: ContactViewModel, navController: NavHostController) {

    val contactos = viewModel.contactos.collectAsState().value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Contactos", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(Color.Red)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.CrearContacto.route)
                    //viewModel.insertarContact(nuevoContacto)
                },
                containerColor = Color.Red
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Agregar", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(15.dp),
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(contactos) { contacto ->
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                navController.navigate("details/${contacto.id}")
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = contacto.imageUrl,
                            contentDescription = contacto.name,
                            modifier = Modifier.size(100.dp)
                        )
                        Text(contacto.name, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
