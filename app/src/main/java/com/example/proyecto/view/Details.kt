package com.example.proyecto.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyecto.viewmodel.ContactViewModel
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalles(
    navController: NavHostController,
    viewModel: ContactViewModel,
    contactoId: Int
) {

    val context = LocalContext.current
    val contacto = viewModel.contactos.collectAsState().value.find { it.id == contactoId }

    if (contacto != null) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Volver",
                            tint = Color.White,
                            modifier = Modifier.clickable { navController.popBackStack() }
                        )
                    },
                    title = { Text("Detalles", color = Color.White) },
                    colors = TopAppBarDefaults.topAppBarColors(Color.Red)
                )
            }
        ) { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = contacto.imageUrl,
                    contentDescription = "Foto del contacto",
                    modifier = Modifier.size(300.dp)
                )
                Spacer(Modifier.height(10.dp))
                Text("Nombre: ${contacto.name}")
                Text("Teléfono: ${contacto.phone}")
                Text("Email: ${contacto.email}")

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.eliminarContact(contacto)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                )
                { Text("Eliminar contacto", color = Color.White) }

                Button(
                    onClick = { navController.navigate("editar/${contacto.id}") },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                )
                { Text("Editar contacto", color = Color.White) }

                Button(
                    onClick = {
                        val url = "https://wa.me/${contacto.phone}"
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = url.toUri()
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) { Text("Compartir en WhatsApp") }

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = "tel:${contacto.phone}".toUri()
                        }
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) { Text("Llamar") }
            }

        }
    } else {
        Text("Contacto no encontrado :_(", modifier = Modifier.padding(20.dp))
    }
}
