// File: src/main/kotlin/ui/components/WelcomeScreen.kt

package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import models.*
import ui.theme.*

@Composable
fun welcomeScreen(
    onNavigateToPatientForm: () -> Unit,
    onNavigateToPatientList: () -> Unit,
    patients: List<Patient>,
    appointments: List<Appointment>,
    statistics: Statistics,
    modifier: Modifier = Modifier
) {
    var selectedPatient by remember { mutableStateOf<Patient?>(null) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            topBar()

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxSize()) {
                navigationSidebar(
                    onNavigateToPatientForm = onNavigateToPatientForm,
                    onNavigateToPatientList = onNavigateToPatientList,
                    modifier = Modifier.width(240.dp)
                )

                Spacer(modifier = Modifier.width(24.dp))

                Column(modifier = Modifier.weight(1f)) {
                    statsSection(statistics)
                    Spacer(modifier = Modifier.height(24.dp))
                    appointmentsSection(appointments)
                    Spacer(modifier = Modifier.height(24.dp))
                    patientsSection(
                        patients = patients,
                        onPatientClick = { selectedPatient = it }
                    )
                }
            }
        }

        selectedPatient?.let { patient ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(16.dp),
                    elevation = 8.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colors.surface,
                ) {
                    PatientDetails(
                        patient = patient,
                        onClose = { selectedPatient = null },
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun topBar() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Bonjour, Dr. Kante",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Bienvenue dans votre tableau de bord",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun navigationSidebar(
    onNavigateToPatientForm: () -> Unit,
    onNavigateToPatientList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Button(
            onClick = onNavigateToPatientList,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Liste des Patients",
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToPatientForm,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onSecondary
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Nouveau Patient",
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun statsSection(statistics: Statistics) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            statItem(
                value = statistics.totalPatients.toString(),
                label = "Total Patients"
            )
            statItem(
                value = statistics.newPatients.toString(),
                label = "Nouveaux Patients"
            )
            statItem(
                value = statistics.totalAppointments.toString(),
                label = "Rendez-vous"
            )
        }
    }
}

@Composable
private fun statItem(value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun appointmentsSection(appointments: List<Appointment>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Rendez-vous d'aujourd'hui",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                "${appointments.size} rendez-vous prévus",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            appointments.forEachIndexed { index, appointment ->
                appointmentItem(appointment)
                if (index < appointments.size - 1) {
                    Divider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun appointmentItem(appointment: Appointment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = appointment.patientName,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = appointment.dateTime,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        }

        Surface(
            color = MaterialTheme.colors.primary.copy(alpha = 0.1f),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = "Prévu",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun patientsSection(
    patients: List<Patient>,
    onPatientClick: (Patient) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Patients Récents",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            patients.forEachIndexed { index, patient ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onPatientClick(patient) },
                    color = MaterialTheme.colors.surface
                ) {
                    patientItem(patient)
                }
                if (index < patients.size - 1) {
                    Divider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun patientItem(patient: Patient) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = patient.name,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = "Né(e) le ${patient.dateOfBirth}",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            if (patient.medicalConditions.depression != Severity.NONE) {
                conditionBadge("Dépressif", MaterialTheme.colors.error)
            }
            if (patient.medicalConditions.insomnia != Severity.NONE) {
                conditionBadge("Insomniaque", MaterialTheme.colors.secondary)
            }
            if (patient.medicalConditions.adhd != Severity.NONE) {
                conditionBadge("ADHD", MaterialTheme.colors.primary)
            }
        }
    }
}

@Composable
private fun conditionBadge(text: String, color: Color) {
    Surface(
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text,
            color = color,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}