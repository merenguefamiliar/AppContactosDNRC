package com.example.proyecto

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.proyecto.view.PantallaCrearContacto
import com.example.proyecto.view.PantallaDetalles
import com.example.proyecto.view.PantallaEditarContacto
import com.example.proyecto.view.PantallaHome
import com.example.proyecto.view.PantallaLogin
import com.example.proyecto.view.Screens
import com.example.proyecto.viewmodel.ContactViewModel

@Composable
fun NavigatorHostController() {
    val navController = androidx.navigation.compose.rememberNavController()
    val viewModel: ContactViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ) {
        composable(Screens.LoginScreen.route) {
            PantallaLogin(navController)
        }
        composable(Screens.HomeScreen.route) {
            PantallaHome(viewModel, navController)
        }

        composable(Screens.CrearContacto.route) {
            PantallaCrearContacto(viewModel, navController)
        }

        composable(
            route = "editar/{contactoId}",
            arguments = listOf(navArgument("contactoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val contactoId = backStackEntry.arguments?.getInt("contactoId") ?: 0
            PantallaEditarContacto(
                navController = navController,
                viewModel = viewModel,
                contactoId = contactoId
            )
        }

        composable(
            route = "details/{contactoId}",
            arguments = listOf(
                navArgument("contactoId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val contactoId = backStackEntry.arguments?.getInt("contactoId") ?: 0

            PantallaDetalles(
                navController = navController,
                viewModel = viewModel,
                contactoId = contactoId
            )
        }
    }

}
