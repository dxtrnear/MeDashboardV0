// File: src/main/kotlin/ui/components/WelcomeScreen.kt

package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.Patient
import models.Appointment
import models.Statistics

@Composable
fun WelcomeScreen(
    onNavigateToPatientForm: () -> Unit,
    onNavigateToPatientList: () -> Unit,
    patients: List<Patient>,
    appointments: List<Appointment>,
    statistics: Statistics,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar()
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            NavigationSidebar(
                modifier = Modifier.width(240.dp),
                onNavigateToPatientForm = onNavigateToPatientForm,
                onNavigateToPatientList = onNavigateToPatientList
            )

            DashboardContent(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                appointments = appointments,
                statistics = statistics,
                patients = patients
            )
        }
    }
}

@Composable
private fun TopAppBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Welcome Dr. Kante!",
            style = MaterialTheme.typography.h4
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search") },
            modifier = Modifier.width(300.dp)
        )
    }
}

@Composable
private fun NavigationSidebar(
    modifier: Modifier = Modifier,
    onNavigateToPatientForm: () -> Unit,
    onNavigateToPatientList: () -> Unit
) {
    Column(modifier = modifier) {
        Button(
            onClick = onNavigateToPatientList,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text("Patients")
        }
        Button(
            onClick = onNavigateToPatientForm,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text("Créer un patient")
        }
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text("Rédiger")
        }
    }
}

@Composable
private fun DashboardContent(
    modifier: Modifier = Modifier,
    appointments: List<Appointment>,
    statistics: Statistics,
    patients: List<Patient>
) {
    Column(modifier = modifier) {
        AppointmentsCard(appointments)
        Spacer(modifier = Modifier.height(16.dp))
        StatisticsCard(statistics)
        Spacer(modifier = Modifier.height(16.dp))
        PatientHistoryCard(patients)
    }
}

@Composable
private fun AppointmentsCard(appointments: List<Appointment>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Rendez-Vous",
                    style = MaterialTheme.typography.h6
                )
                Text(appointments.size.toString())
            }
            Spacer(modifier = Modifier.height(8.dp))
            appointments.take(3).forEach { appointment ->
                AppointmentItem(appointment.patientName, appointment.dateTime)
            }
        }
    }
}

@Composable
private fun StatisticsCard(statistics: Statistics) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Statistics",
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Total Patients: ${statistics.totalPatients}")
            Text("New Patients: ${statistics.newPatients}")
            Text("Total Appointments: ${statistics.totalAppointments}")
        }
    }
}

@Composable
private fun PatientHistoryCard(patients: List<Patient>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Historique des Patients",
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            patients.take(4).forEach { patient ->
                AppointmentItem(patient.name, patient.createdAt.toString())
            }
        }
    }
}

@Composable
private fun AppointmentItem(name: String, datetime: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(name)
        Text(datetime)
    }
}