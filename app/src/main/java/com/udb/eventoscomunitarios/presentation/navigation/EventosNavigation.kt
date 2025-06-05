package com.udb.eventoscomunitarios.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.udb.eventoscomunitarios.presentation.screens.WelcomeScreen
import com.udb.eventoscomunitarios.presentation.screens.auth.LoginScreen
import com.udb.eventoscomunitarios.presentation.screens.auth.RegisterScreen
import com.udb.eventoscomunitarios.presentation.screens.events.DashboardScreen
import com.udb.eventoscomunitarios.presentation.screens.events.EventDetailScreen
import com.udb.eventoscomunitarios.presentation.screens.events.CreateEventScreen
import com.udb.eventoscomunitarios.presentation.screens.profile.ProfileScreen

@Composable
fun EventosNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        // Pantalla de Bienvenida
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
                onNavigateBack = {
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
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // Dashboard Principal
        composable("dashboard") {
            DashboardScreen(
                onNavigateToProfile = {
                    navController.navigate("profile")
                },
                onNavigateToEventDetail = { eventId ->
                    navController.navigate("event_detail/$eventId")
                },
                onNavigateToCreateEvent = {
                    navController.navigate("create_event")
                },
                onLogout = {
                    navController.navigate("welcome") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }

        // Detalle de Evento (con parámetro eventId)
        composable(
            route = "event_detail/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.StringType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId") ?: "1"
            EventDetailScreen(
                eventId = eventId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onRegisterForEvent = {
                    // TODO: Implementar lógica de registro real
                    // Por ahora solo muestra un mensaje o cambia estado
                },
                onShareEvent = {
                    // TODO: Implementar compartir real
                    // Por ahora solo un placeholder
                }
            )
        }

        // Crear Evento
        composable("create_event") {
            CreateEventScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onCreateEvent = {
                    // TODO: Implementar creación real de eventos
                    // Por ahora regresar al dashboard
                    navController.popBackStack()
                }
            )
        }

        // Perfil de Usuario
        composable("profile") {
            ProfileScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToSettings = {
                    navController.navigate("settings")
                },
                onLogout = {
                    navController.navigate("welcome") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }

        // Ruta temporal para Profile (mientras lo creas)
        composable("profile") {
            // Pantalla temporal que redirige al dashboard
            DashboardScreen(
                onNavigateToProfile = { },
                onNavigateToEventDetail = { eventId ->
                    navController.navigate("event_detail/$eventId")
                },
                onNavigateToCreateEvent = {
                    navController.navigate("create_event")
                },
                onLogout = {
                    navController.navigate("welcome") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }
    }
}