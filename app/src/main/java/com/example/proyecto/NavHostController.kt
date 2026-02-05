package com.example.proyecto

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gestortareasdnt.screens.PantallaHome
import com.example.proyecto.view.PantallaDetalles
import com.example.proyecto.view.PantallaLogin
import com.example.proyecto.view.Screens

@Composable
fun NavigatorHostController() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = TweenSpec(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = TweenSpec(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = TweenSpec(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = TweenSpec(700)
            )
        },
    ) {
        composable(Screens.LoginScreen.route) { PantallaLogin(navController) }
        composable(Screens.HomeScreen.route) { PantallaHome(navController)}
        composable(
            "${Screens.DetailScreen.route}/{nombre}/{edad}/{urlImagen}/{bio}",
            arguments = listOf(
                navArgument("nombre") {
                    type = NavType.StringType
                },
                navArgument("edad") {
                    type = NavType.IntType
                },
                navArgument("urlImagen") {
                    type = NavType.StringType
                },
                navArgument("bio") {
                    type = NavType.StringType
                })

        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre")
            val edad = backStackEntry.arguments?.getInt("edad")
            val urlImagen = backStackEntry.arguments?.getString("urlImagen")
            val bio = backStackEntry.arguments?.getString("bio")

            PantallaDetalles(navController, nombre, edad, urlImagen, bio)
        }

    }
}