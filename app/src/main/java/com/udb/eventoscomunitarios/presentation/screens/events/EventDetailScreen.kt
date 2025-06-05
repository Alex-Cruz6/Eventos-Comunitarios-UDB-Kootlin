package com.udb.eventoscomunitarios.presentation.screens.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(
    eventId: String = "1",
    onNavigateBack: () -> Unit = {},
    onRegisterForEvent: () -> Unit = {},
    onShareEvent: () -> Unit = {}
) {
    var isRegistered by remember { mutableStateOf(false) }

    // Sample event data - en producción esto vendría de Firebase
    val eventData = when (eventId) {
        "1" -> mapOf(
            "title" to "Festival Cultural UDB",
            "emoji" to "🎭",
            "date" to "Viernes, 25 Mayo",
            "time" to "2:00 PM - 6:00 PM",
            "location" to "Campus UDB",
            "address" to "Auditorio Principal",
            "attendees" to "45",
            "description" to "Celebremos la diversidad cultural con música, danza y comida tradicional. Un evento para toda la comunidad UDB.",
            "organizer" to "Club Cultural UDB",
            "organizerInitials" to "CC"
        )
        "2" -> mapOf(
            "title" to "Charla de Inteligencia Artificial",
            "emoji" to "💻",
            "date" to "Lunes, 28 Mayo",
            "time" to "10:00 AM - 12:00 PM",
            "location" to "Aula Magna",
            "address" to "Campus Central",
            "attendees" to "12",
            "description" to "Explora el futuro de la inteligencia artificial y sus aplicaciones en el mundo moderno. Una charla técnica para estudiantes y profesionales.",
            "organizer" to "Departamento de Ingeniería",
            "organizerInitials" to "DI"
        )
        "3" -> mapOf(
            "title" to "Torneo Inter-Facultades",
            "emoji" to "⚽",
            "date" to "Miércoles, 30 Mayo",
            "time" to "8:00 AM - 5:00 PM",
            "location" to "Campo Deportivo",
            "address" to "Instalaciones UDB",
            "attendees" to "8",
            "description" to "Competencia deportiva entre las diferentes facultades de la universidad. ¡Ven a apoyar a tu facultad favorita!",
            "organizer" to "Departamento de Deportes",
            "organizerInitials" to "DD"
        )
        else -> mapOf(
            "title" to "Evento no encontrado",
            "emoji" to "❓",
            "date" to "",
            "time" to "",
            "location" to "",
            "address" to "",
            "attendees" to "0",
            "description" to "",
            "organizer" to "",
            "organizerInitials" to "?"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        TopAppBar(
            title = {
                Text(
                    text = eventData["title"] ?: "Evento",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = UDBBlue
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Event Image Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(UDBOrange),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = eventData["emoji"] ?: "🎭",
                    fontSize = 64.sp
                )
            }

            // Event Details
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                // Date and Time
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Surface(
                        color = Color.Gray,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.size(16.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = eventData["date"] ?: "",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = eventData["time"] ?: "",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

                // Location
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Surface(
                        color = Color.Gray,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.size(16.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = eventData["location"] ?: "",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = eventData["address"] ?: "",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

                // Attendees
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Surface(
                        color = Color.Gray,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.size(16.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "${eventData["attendees"]} asistentes confirmados",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                // Description
                Column(
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = "📝",
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Descripción:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Text(
                        text = eventData["description"] ?: "",
                        fontSize = 14.sp,
                        color = Color.Black,
                        lineHeight = 20.sp
                    )
                }

                // Action Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            isRegistered = !isRegistered
                            onRegisterForEvent()
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isRegistered) Color.Gray else Color(0xFF4CAF50)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = if (isRegistered) "🎫 Registrado" else "🎫 Asistir",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    OutlinedButton(
                        onClick = onShareEvent,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "📤 Compartir",
                            color = UDBBlue,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Organizer
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Organizador:",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                modifier = Modifier.size(40.dp),
                                shape = RoundedCornerShape(20.dp),
                                color = UDBBlue
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = eventData["organizerInitials"] ?: "?",
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = eventData["organizer"] ?: "",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventDetailScreenPreview() {
    EventosComunitariosUDBTheme {
        EventDetailScreen()
    }
}