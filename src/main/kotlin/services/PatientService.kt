// File: src/main/kotlin/services/PatientService.kt

package services

import models.*
import java.time.LocalDateTime
import java.util.UUID

class PatientService {
    private val patients = mutableListOf<Patient>()
    private val appointments = mutableListOf<Appointment>()

    init {
        addSampleData()
    }

    private fun addSampleData() {
        addPatient(
            Patient(
                id = UUID.randomUUID().toString(),
                name = "Vincent Van Gogh",
                dateOfBirth = "30/03/1853",
                medicalConditions = MedicalConditions(
                    depression = Severity.SEVERE,
                    insomnia = Severity.MODERATE,
                    adhd = Severity.NONE
                ),
                remarks = "Patient shows signs of anxiety"
            )
        )

        appointments.addAll(
            listOf(
                Appointment(
                    id = UUID.randomUUID().toString(),
                    patientName = "Patrick Sebastien",
                    dateTime = "25 Jan, 2023 | 04:00 PM"
                ),
                Appointment(
                    id = UUID.randomUUID().toString(),
                    patientName = "Lola",
                    dateTime = "25 Jan, 2023 | 04:00 PM"
                )
            )
        )
    }

    fun addPatient(patient: Patient) {
        patients.add(patient)
    }

    fun getPatients(): List<Patient> = patients.toList()

    fun getAppointments(): List<Appointment> = appointments.toList()

    fun getStatistics(): Statistics {
        return Statistics(
            totalPatients = patients.size,
            newPatients = patients.count {
                it.createdAt.monthValue == LocalDateTime.now().monthValue
            },
            totalAppointments = appointments.size
        )
    }
}