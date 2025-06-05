package com.udb.eventoscomunitarios.presentation.screens.events

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udb.eventoscomunitarios.presentation.theme.EventosComunitariosUDBTheme
import com.udb.eventoscomunitarios.presentation.theme.UDBBlue
import com.udb.eventoscomunitarios.presentation.theme.UDBOrange
import kotlinx.coroutines.launch

data class EventItem(
    val id: String,
    val title: String,
    val date: String,
    val location: String,
    val attendees: String,
    val emoji: String,
    val category: String,
    val status: String = "Activo"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToProfile: () -> Unit = {},
    onNavigateToEventDetail: (String) -> Unit = {},
    onNavigateToCreateEvent: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Sample data según mockups
    val sampleEvents = listOf(
        EventItem("1", "Festival Cultural UDB", "25 May - 2:00 PM", "Campus UDB", "45 asistentes", "🎭", "Cultural"),
        EventItem("2", "Charla de Inteligencia Artificial", "28 May - 10:00 AM", "Aula Magna", "12 asistentes", "💻", "Académico"),
        EventItem("3", "Torneo Inter-Facultades", "30 May - 8:00 AM", "Campo Deportivo", "8 equipos", "⚽", "Deportes")
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onNavigateToProfile = {
                    scope.launch { drawerState.close() }
                    onNavigateToProfile()
                },
                onLogout = {
                    scope.launch { drawerState.close() }
                    onLogout()
                }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Top Bar
                TopAppBar(
                    title = {
                        Text(
                            text = "Eventos UDB",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* TODO: Notifications */ }) {
                            Icon(
                                Icons.Default.Notifications,
                                contentDescription = "Notificaciones",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = UDBBlue
                    )
                )

                // Search Bar
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Buscar eventos...",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                }

                // Próximos Eventos Section
                Text(
                    text = "Próximos Eventos",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                // Events List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(sampleEvents) { event ->
                        EventCard(
                            event = event,
                            onEventClick = { onNavigateToEventDetail(event.id) }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(80.dp)) // Space for FAB
                    }
                }
            }

            // Floating Action Button
            FloatingActionButton(
                onClick = onNavigateToCreateEvent,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = UDBOrange
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Crear Evento",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun DrawerContent(
    onNavigateToProfile: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            color = UDBBlue,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier.size(60.dp),
                    shape = RoundedCornerShape(30.dp),
                    color = Color.White
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "JP",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = UDBBlue
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Juan Pérez",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "juan@udb.edu.sv",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        // Menu Items
        DrawerMenuItem(
            icon = "👤",
            title = "Mi Perfil",
            onClick = onNavigateToProfile
        )

        DrawerMenuItem(
            icon = "📋",
            title = "Mis Eventos",
            onClick = { /* TODO */ }
        )

        DrawerMenuItem(
            icon = "🎟️",
            title = "Eventos Asistidos",
            onClick = { /* TODO */ }
        )

        DrawerMenuItem(
            icon = "⚙️",
            title = "Configuración",
            onClick = { /* TODO */ }
        )

        DrawerMenuItem(
            icon = "📄",
            title = "Licencias CC",
            onClick = { /* TODO */ }
        )

        Spacer(modifier = Modifier.weight(1f))

        // Logout
        Divider(color = Color.Gray.copy(alpha = 0.3f))

        TextButton(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🚪",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(end = 12.dp)
                )
                Text(
                    text = "Cerrar Sesión",
                    fontSize = 16.sp,
                    color = Color(0xFFE74C3C),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun DrawerMenuItem(
    icon: String,
    title: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun EventCard(
    event: EventItem,
    onEventClick: () -> Unit = {}
) {
    var isRegistered by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEventClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Event Image/Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(UDBOrange),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${event.emoji} ${event.title.split(" ").take(2).joinToString(" ")}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // Event Content
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = event.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Date
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Surface(
                        color = Color.Gray,
                        shape = RoundedCornerShape(2.dp),
                        modifier = Modifier.size(16.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = event.date,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                // Location
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Surface(
                        color = Color.Gray,
                        shape = RoundedCornerShape(2.dp),
                        modifier = Modifier.size(16.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = event.location,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                // Attendees
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Surface(
                        color = Color.Gray,
                        shape = RoundedCornerShape(2.dp),
                        modifier = Modifier.size(16.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = event.attendees,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                // Status and Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = Color(0xFF4CAF50),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = event.status,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }

                    Button(
                        onClick = {
                            isRegistered = !isRegistered
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isRegistered) Color.Gray else UDBBlue
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = if (isRegistered) "Registrado" else "Asistir",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    EventosComunitariosUDBTheme {
        DashboardScreen()
    }
}