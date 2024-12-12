// File: src/main/kotlin/Main.kt

import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.components.*
import ui.navigation.Screen
import ui.theme.medashboardTheme
import viewmodel.PatientViewModel
import services.PatientService

fun main() = application {
    val patientService = remember { PatientService() }
    val viewModel = remember { PatientViewModel(patientService) }

    Window(onCloseRequest = ::exitApplication, title = "MeDashboard") {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.WELCOME) }

        medashboardTheme {
            when (currentScreen) {
                Screen.WELCOME -> welcomeScreen(
                    onNavigateToPatientForm = { currentScreen = Screen.PATIENT_FORM },
                    onNavigateToPatientList = { currentScreen = Screen.PATIENT_LIST },
                    patients = viewModel.patients,
                    appointments = viewModel.appointments,
                    statistics = viewModel.statistics
                )
                Screen.PATIENT_FORM -> PatientForm(
                    onNavigateBack = { currentScreen = Screen.WELCOME },
                    onSave = { patient ->
                        viewModel.addPatient(patient)
                        currentScreen = Screen.WELCOME
                    }
                )
                Screen.PATIENT_LIST -> PatientListScreen(
                    onNavigateBack = { currentScreen = Screen.WELCOME },
                    patients = viewModel.patients
                )
            }
        }
    }
}