package com.example.data.model

enum class AttendanceStatus {
    PRESENT, MISSED, EXCUSED
}

enum class SessionType {
    NIGHT, EVENING, MORNING
}

data class AttendanceRecord(
    val id: String,
    val title: String,
    val timestamp: String,
    val date: String,
    val sessionType: SessionType,
    val status: AttendanceStatus
)

data class StudentProfile(
    val id: String = "24AIM001",
    val fullName: String = "Devesh Dwivedi",
    val firstName: String = "Devesh",
    val studentId: String = "24AIM001",
    val hostelName: String = "Charak Chatravas",
    val university: String = "VBSPU, Jaunpur",
    val roomNumber: String = "214",
    val floorNumber: String = "2",
    val wardenName: String = "Dr. S. Mishra",
    val wardenPhone: String = "+91 94567 89012",
    val gatePhone: String = "+91 94567 89011",
    val emergencyPhone: String = "+91 94567 89000",
    val email: String = "devesh4678@gmail.com",
    val course: String = "B.Tech Computer Science & AI",
    val year: String = "2nd Year"
)

data class Notice(
    val id: String,
    val title: String,
    val content: String,
    val date: String,
    val category: String,
    val isNew: Boolean = false
)

data class MessMeal(
    val mealType: String,
    val time: String,
    val items: List<String>,
    val highlight: String
)

data class AttendanceStats(
    val monthName: String = "September 2026",
    val presentCount: Int = 22,
    val missedCount: Int = 3,
    val totalSessions: Int = 25
) {
    val percentage: Int
        get() = if (totalSessions > 0) ((presentCount * 100) / totalSessions) else 0
}
