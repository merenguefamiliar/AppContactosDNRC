package com.example.proyecto.Screens

sealed class Screens (val route : String){

    object HomeScreen : Screens("home")
    object DetailScreen : Screens("details")
    object LoginScreen : Screens("login")
}