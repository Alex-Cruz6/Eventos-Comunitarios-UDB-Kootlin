package com.udb.eventoscomunitarios.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.udb.eventoscomunitarios.presentation.screens.WelcomeScreen
import com.udb.eventoscomunitarios.presentation.screens.auth.LoginScreen
import com.udb.eventoscomunitarios.presentation.screens.auth.RegisterScreen
import com.udb.eventoscomunitarios.presentation.screens.events.DashboardScreen

@Composable
fun EventosNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"  // ← CAMBIO PRINCIPAL: Empezar en welcome
    ) {
        // Pantalla de Bienvenida (NUEVA)
        composable("welcome") {
            WelcomeScreen(
                onNavigateToLogin = {
                    navController.navigate("login")
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                },
                onContinueWithoutAccount = {
                    navController.navigate("dashboard")
                }
            )
        }

        // Pantalla de Login
        composable("login") {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate("register")
                },
                onNavigateToDashboard = {
                    navController.navigate("dashboard") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                onNavigateBack = {  // NUEVO: Volver a bienvenida
                    navController.popBackStack()
                }
            )
        }

        // Pantalla de Registro
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateToDashboard = {
                    navController.navigate("dashboard") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                onNavigateBack = {  // NUEVO: Volver a bienvenida
                    navController.popBackStack()
                }
            )
        }

        // Dashboard
        composable("dashboard") {
            DashboardScreen()
        }
    }
}
