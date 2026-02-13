package com.example.proyecto.view

sealed class Screens (val route : String){

    object HomeScreen : Screens("home")
    object DetailScreen : Screens("details")
    object LoginScreen : Screens("login")
    object CrearContacto : Screens("crear")
    object ActualizarContacto : Screens("actualizar")
}