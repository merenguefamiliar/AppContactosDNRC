package com.example.proyecto.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyecto.viewmodel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarContacto(navController: NavHostController, viewModel: ContactViewModel, contactoId: Int)
{
    val contacto = viewModel.contactos.collectAsState().value.find { it.id == contactoId }

    if (contacto != null) {
        // igual q borrar pero carga los datos q ya tiene pa q no esté vacio
        var nombre by rememberSaveable { mutableStateOf(contacto.name) }
        var telefono by rememberSaveable { mutableStateOf(contacto.phone) }
        var email by rememberSaveable { mutableStateOf(contacto.email) }
        var imagenUrl by rememberSaveable { mutableStateOf(contacto.imageUrl) }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Editar contacto", color = Color.White) },
                    colors = TopAppBarDefaults.topAppBarColors(Color.Red)
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )
                Spacer(Modifier.height(8.dp))
                TextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") }
                )
                Spacer(Modifier.height(8.dp))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") }
                )
                Spacer(Modifier.height(8.dp))
                TextField(
                    value = imagenUrl,
                    onValueChange = { imagenUrl = it },
                    label = { Text("URL de la imagen") }
                )

                Spacer(Modifier.height(16.dp))

                Button(onClick = {
                        if (nombre.isNotBlank() && telefono.isNotBlank() && email.isNotBlank()) {
                            val contactoActualizado = contacto.copy(
                                name = nombre,
                                phone = telefono,
                                email = email,
                                imageUrl = imagenUrl
                            )
                            viewModel.actualizarContact(contactoActualizado)
                            navController.popBackStack()
                        }
                    }, colors = ButtonDefaults.buttonColors(Color.Red)) {
                    Text("Guardar cambios", color = Color.White)
                }
            }
        }

    } else {
        Text("Contacto no encontrado XD", modifier = Modifier.padding(20.dp))
    }
}