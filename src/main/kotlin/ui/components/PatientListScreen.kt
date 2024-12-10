// File: src/main/kotlin/ui/components/PatientListScreen.kt

package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.Patient
import models.MedicalConditions
import models.Severity

@Composable
fun PatientListScreen(
    onNavigateBack: () -> Unit,
    patients: List<Patient>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Liste des Patients") },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Text("←")
                }
            }
        )

        var searchQuery by remember { mutableStateOf("") }
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Rechercher un patient") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        if (patients.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text("Aucun patient enregistré")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                val filteredPatients = patients.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }
                items(filteredPatients) { patient ->
                    PatientCard(patient)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun PatientCard(patient: Patient) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(patient.name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Date de naissance: ${patient.dateOfBirth}")

            // Display medical conditions
            val conditions = mutableListOf<String>()
            with(patient.medicalConditions) {
                if (insomnia != Severity.NONE) conditions.add("Insomniaque: $insomnia")
                if (depression != Severity.NONE) conditions.add("Dépressif: $depression")
                if (adhd != Severity.NONE) conditions.add("ADHD: $adhd")
            }
            if (conditions.isNotEmpty()) {
                Text("Conditions: ${conditions.joinToString(", ")}")
            }

            // Display remarks if any
            if (patient.remarks.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text("Remarques: ${patient.remarks}")
            }
        }
    }
}