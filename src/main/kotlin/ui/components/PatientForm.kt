// File: src/main/kotlin/ui/components/PatientForm.kt

package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.*
import java.time.LocalDateTime

@Composable
fun PatientForm(
    onNavigateBack: () -> Unit,
    onSave: (Patient) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var remarks by remember { mutableStateOf("") }
    var insomniaState by remember { mutableStateOf(Severity.NONE) }
    var depressionState by remember { mutableStateOf(Severity.NONE) }
    var adhdState by remember { mutableStateOf(Severity.NONE) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Top Bar
        TopAppBar(
            title = { Text("Nouveau Patient") },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Text("←")
                }
            }
        )

        // Form Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nom et Prenom") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = dateOfBirth,
                onValueChange = { dateOfBirth = it },
                label = { Text("Date de naissance") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Medical Conditions
            Text(
                "Conditions Médicales",
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(16.dp))

            ConditionSelector("Insomniaque", insomniaState) { insomniaState = it }
            ConditionSelector("Dépressif", depressionState) { depressionState = it }
            ConditionSelector("ADHD", adhdState) { adhdState = it }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = remarks,
                onValueChange = { remarks = it },
                label = { Text("Remarques") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    onSave(
                        Patient(
                            name = name,
                            dateOfBirth = dateOfBirth,
                            medicalConditions = MedicalConditions(
                                insomnia = insomniaState,
                                depression = depressionState,
                                adhd = adhdState
                            ),
                            remarks = remarks,
                            createdAt = LocalDateTime.now()
                        )
                    )
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Enregistrer")
            }
        }
    }
}

@Composable
private fun ConditionSelector(
    label: String,
    selectedSeverity: Severity,
    onSeverityChange: (Severity) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(label)
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Severity.values().forEach { severity ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    RadioButton(
                        selected = selectedSeverity == severity,
                        onClick = { onSeverityChange(severity) }
                    )
                    Text(
                        text = severity.name,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}