package com.example.data

import com.example.data.model.AttendanceRecord
import com.example.data.model.AttendanceStats
import com.example.data.model.AttendanceStatus
import com.example.data.model.MessMeal
import com.example.data.model.Notice
import com.example.data.model.SessionType
import com.example.data.model.StudentProfile
import com.example.ui.theme.AppThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HostelRepository {

    private val _isActivated = MutableStateFlow(true)
    val isActivated: StateFlow<Boolean> = _isActivated.asStateFlow()

    private val _studentProfile = MutableStateFlow(StudentProfile())
    val studentProfile: StateFlow<StudentProfile> = _studentProfile.asStateFlow()

    // Attendance Session state
    // By default, let attendance session be open so user can experience the "Attendance is open" card & marking flow!
    private val _isAttendanceOpen = MutableStateFlow(true)
    val isAttendanceOpen: StateFlow<Boolean> = _isAttendanceOpen.asStateFlow()

    private val _isAttendanceMarked = MutableStateFlow(false)
    val isAttendanceMarked: StateFlow<Boolean> = _isAttendanceMarked.asStateFlow()

    private val _lastMarkedTime = MutableStateFlow("2 Oct 2026 • 08:12 PM")
    val lastMarkedTime: StateFlow<String> = _lastMarkedTime.asStateFlow()

    private val _themeMode = MutableStateFlow(AppThemeMode.SYSTEM)
    val themeMode: StateFlow<AppThemeMode> = _themeMode.asStateFlow()

    private val _attendanceRecords = MutableStateFlow(
        listOf(
            AttendanceRecord(
                id = "att_1",
                title = "Night Attendance",
                timestamp = "08:12 PM",
                date = "2 Oct 2026",
                sessionType = SessionType.NIGHT,
                status = AttendanceStatus.PRESENT
            ),
            AttendanceRecord(
                id = "att_2",
                title = "Evening Attendance",
                timestamp = "08:05 PM",
                date = "1 Oct 2026",
                sessionType = SessionType.EVENING,
                status = AttendanceStatus.PRESENT
            ),
            AttendanceRecord(
                id = "att_3",
                title = "Morning Attendance",
                timestamp = "07:50 AM",
                date = "30 Sep 2026",
                sessionType = SessionType.MORNING,
                status = AttendanceStatus.PRESENT
            ),
            AttendanceRecord(
                id = "att_4",
                title = "Night Attendance",
                timestamp = "08:10 PM",
                date = "29 Sep 2026",
                sessionType = SessionType.NIGHT,
                status = AttendanceStatus.MISSED
            ),
            AttendanceRecord(
                id = "att_5",
                title = "Night Attendance",
                timestamp = "08:15 PM",
                date = "28 Sep 2026",
                sessionType = SessionType.NIGHT,
                status = AttendanceStatus.PRESENT
            ),
            AttendanceRecord(
                id = "att_6",
                title = "Evening Attendance",
                timestamp = "08:00 PM",
                date = "27 Sep 2026",
                sessionType = SessionType.EVENING,
                status = AttendanceStatus.PRESENT
            ),
            AttendanceRecord(
                id = "att_7",
                title = "Night Attendance",
                timestamp = "08:08 PM",
                date = "26 Sep 2026",
                sessionType = SessionType.NIGHT,
                status = AttendanceStatus.PRESENT
            ),
            AttendanceRecord(
                id = "att_8",
                title = "Morning Attendance",
                timestamp = "07:45 AM",
                date = "25 Sep 2026",
                sessionType = SessionType.MORNING,
                status = AttendanceStatus.PRESENT
            ),
            AttendanceRecord(
                id = "att_9",
                title = "Night Attendance",
                timestamp = "08:20 PM",
                date = "24 Sep 2026",
                sessionType = SessionType.NIGHT,
                status = AttendanceStatus.MISSED
            ),
            AttendanceRecord(
                id = "att_10",
                title = "Evening Attendance",
                timestamp = "08:02 PM",
                date = "23 Sep 2026",
                sessionType = SessionType.EVENING,
                status = AttendanceStatus.PRESENT
            )
        )
    )
    val attendanceRecords: StateFlow<List<AttendanceRecord>> = _attendanceRecords.asStateFlow()

    private val _notices = MutableStateFlow(
        listOf(
            Notice(
                id = "n_1",
                title = "Hostel Maintenance & Water Supply Schedule",
                content = "Routine overhead tank cleaning is scheduled tomorrow between 10:00 AM and 02:00 PM. Please store required water in advance.",
                date = "2 Oct 2026",
                category = "Maintenance",
                isNew = true
            ),
            Notice(
                id = "n_2",
                title = "Night Roll Call Timing Update",
                content = "Night attendance window is strictly 08:00 PM to 10:30 PM. Late entries beyond 10:45 PM require prior approval from the Chief Warden.",
                date = "1 Oct 2026",
                category = "General",
                isNew = true
            ),
            Notice(
                id = "n_3",
                title = "Inter-Hostel Badminton & Cricket Championship",
                content = "Registration for the VBSPU annual inter-hostel sports tournament is open. Submit team lists at the sports secretary office by 5th October.",
                date = "28 Sep 2026",
                category = "Sports",
                isNew = false
            ),
            Notice(
                id = "n_4",
                title = "Wi-Fi Upgradation in Charak Chatravas",
                content = "High-speed optical fiber routers installed on floors 1, 2 and 3. Contact the IT coordinator if you experience signal drops.",
                date = "25 Sep 2026",
                category = "Facilities",
                isNew = false
            )
        )
    )
    val notices: StateFlow<List<Notice>> = _notices.asStateFlow()

    val messMeals: List<MessMeal> = listOf(
        MessMeal(
            mealType = "Breakfast",
            time = "07:30 AM - 09:30 AM",
            items = listOf("Poha with Bhujia & Lemon", "Boiled Eggs / Fresh Banana", "Masala Chai / Hot Milk", "Brown Bread with Butter & Jam"),
            highlight = "Special Masala Poha"
        ),
        MessMeal(
            mealType = "Lunch",
            time = "12:30 PM - 02:30 PM",
            items = listOf("Punjabi Rajma Masala", "Steamed Basmati Rice", "Tawa Butter Roti", "Boondi Raita", "Kachumber Salad & Pickle"),
            highlight = "Chef's Special Rajma"
        ),
        MessMeal(
            mealType = "Evening Snacks",
            time = "05:00 PM - 06:15 PM",
            items = listOf("Crispy Veg Samosa (2 pcs)", "Mint Chutney & Saunth", "Ginger Cardamom Tea / Coffee"),
            highlight = "Hot Samosas"
        ),
        MessMeal(
            mealType = "Dinner",
            time = "08:00 PM - 10:00 PM",
            items = listOf("Shahi Paneer / Kadhai Chicken", "Yellow Dal Tadka", "Jeera Rice", "Soft Phulka Roti", "Hot Gulab Jamun (1 pc)"),
            highlight = "Paneer & Sweet Night"
        )
    )

    fun activateAccount(code: String): Boolean {
        // Any 6-char code is valid for test ease, e.g. 24AIM1 or 123456
        if (code.length >= 4) {
            _isActivated.value = true
            return true
        }
        return false
    }

    fun markAttendance(): AttendanceRecord {
        val newRecord = AttendanceRecord(
            id = "att_${System.currentTimeMillis()}",
            title = "Night Attendance",
            timestamp = "08:12 PM",
            date = "2 Oct 2026",
            sessionType = SessionType.NIGHT,
            status = AttendanceStatus.PRESENT
        )
        _isAttendanceMarked.value = true
        _lastMarkedTime.value = "2 Oct 2026 • 08:12 PM"
        _attendanceRecords.update { current ->
            // Replace today's night attendance or insert at front
            val filtered = current.filterNot { it.date == "2 Oct 2026" && it.title == "Night Attendance" }
            listOf(newRecord) + filtered
        }
        return newRecord
    }

    fun toggleSessionOpen() {
        _isAttendanceOpen.update { !it }
        if (_isAttendanceOpen.value) {
            _isAttendanceMarked.value = false
        }
    }

    fun resetAttendanceForTesting() {
        _isAttendanceMarked.value = false
        _isAttendanceOpen.value = true
    }

    fun setThemeMode(mode: AppThemeMode) {
        _themeMode.value = mode
    }

    fun signOut() {
        _isActivated.value = false
        _isAttendanceMarked.value = false
    }

    fun getStats(): AttendanceStats {
        val records = _attendanceRecords.value
        val present = records.count { it.status == AttendanceStatus.PRESENT }
        val missed = records.count { it.status == AttendanceStatus.MISSED }
        return AttendanceStats(
            monthName = "September 2026",
            presentCount = 22 + (if (_isAttendanceMarked.value) 1 else 0),
            missedCount = 3,
            totalSessions = 25 + (if (_isAttendanceMarked.value) 1 else 0)
        )
    }
}
