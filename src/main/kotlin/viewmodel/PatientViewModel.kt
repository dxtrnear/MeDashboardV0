// File: src/main/kotlin/viewmodel/PatientViewModel.kt

package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import models.*
import services.PatientService

class PatientViewModel(private val patientService: PatientService) {
    var patients by mutableStateOf<List<Patient>>(emptyList())
        private set

    var appointments by mutableStateOf<List<Appointment>>(emptyList())
        private set

    var statistics by mutableStateOf(Statistics())
        private set

    init {
        loadData()
    }

    private fun loadData() {
        patients = patientService.getPatients()
        appointments = patientService.getAppointments()
        statistics = patientService.getStatistics()
        println("Nombre de patients: ${patients.size} patients") // Debug log
    }

    fun addPatient(patient: Patient) {
        println("Ajout d'un patient: ${patient.name}") // Debug log
        patientService.addPatient(patient)
        loadData()
    }
}