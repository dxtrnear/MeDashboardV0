// File: src/main/kotlin/models/Models.kt

package models

import java.time.LocalDateTime

data class Patient(
    val id: String = "",
    val name: String = "",
    val dateOfBirth: String = "",
    val medicalConditions: MedicalConditions = MedicalConditions(),
    val remarks: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now()
)

data class MedicalConditions(
    val insomnia: Severity = Severity.NONE,
    val depression: Severity = Severity.NONE,
    val adhd: Severity = Severity.NONE
)

enum class Severity {
    NONE,
    MODERATE,
    SEVERE
}

data class Appointment(
    val id: String = "",
    val patientName: String,
    val dateTime: String
)

data class Statistics(
    val totalPatients: Int = 0,
    val newPatients: Int = 0,
    val totalAppointments: Int = 0
)