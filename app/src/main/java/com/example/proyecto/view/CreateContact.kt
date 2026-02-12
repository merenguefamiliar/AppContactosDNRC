package com.example.proyecto.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyecto.data.local.ContactEntity
import com.example.proyecto.viewmodel.ContactViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCrearContacto(viewModel: ContactViewModel, navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Crear contacto", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(Color.Red)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var nombre by rememberSaveable { mutableStateOf("") }
            var telefono by rememberSaveable { mutableStateOf("") }
            var email by rememberSaveable { mutableStateOf("") }
            var imagenUrl by rememberSaveable { mutableStateOf("") }

            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = imagenUrl,
                onValueChange = { imagenUrl = it },
                label = { Text("URL de la imagen") }
            )

            Button(
                onClick = {
                    if (nombre.isNotBlank() && telefono.isNotBlank() && email.isNotBlank()) {
                        if (imagenUrl.isBlank())
                            imagenUrl = "https://i.imgur.com/y5kzaDK.png"
                        val contacto = ContactEntity(
                            name = nombre,
                            phone = telefono,
                            email = email,
                            imageUrl = imagenUrl
                        )
                        viewModel.insertarContact(contacto)
                        navController.popBackStack()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Icon(imageVector = Icons.Filled.Done, contentDescription = "Crear")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Crear contacto", color = Color.White)
            }
        }
    }
}