// File: src/main/kotlin/ui/components/PatientDetails.kt

package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.Patient
import models.Severity
import ui.theme.*

@Composable
fun PatientDetails(
    patient: Patient,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = patient.name,
                style = MaterialTheme.typography.h5
            )
            IconButton(onClick = onClose) {
                Text("×", style = MaterialTheme.typography.h5)
            }
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Patient Info
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Informations Personnelles",
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Date de naissance: ${patient.dateOfBirth}")

            if (patient.remarks.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Remarques: ${patient.remarks}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Medical Conditions
        Text(
            "Conditions Médicales",
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Display each condition
        Column {
            with(patient.medicalConditions) {
                if (insomnia != Severity.NONE) {
                    ConditionRow("Insomnie", insomnia)
                }
                if (depression != Severity.NONE) {
                    ConditionRow("Dépression", depression)
                }
                if (adhd != Severity.NONE) {
                    ConditionRow("ADHD", adhd)
                }
            }
        }
    }
}

@Composable
private fun ConditionRow(condition: String, severity: Severity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(condition)
        Card(
            backgroundColor = when (severity) {
                Severity.MODERATE -> MedicalBlue.copy(alpha = 0.1f)
                Severity.SEVERE -> Error.copy(alpha = 0.1f)
                Severity.NONE -> TextSecondary.copy(alpha = 0.1f)
            },
            elevation = 0.dp
        ) {
            Text(
                text = when (severity) {
                    Severity.MODERATE -> "Modéré"
                    Severity.SEVERE -> "Sévère"
                    Severity.NONE -> "Aucun"
                },
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                color = when (severity) {
                    Severity.MODERATE -> MedicalBlue
                    Severity.SEVERE -> Error
                    Severity.NONE -> TextSecondary
                }
            )
        }
    }
}