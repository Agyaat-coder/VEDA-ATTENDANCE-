package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.HostelRepository
import com.example.data.model.AttendanceRecord
import com.example.data.model.AttendanceStats
import com.example.data.model.MessMeal
import com.example.data.model.Notice
import com.example.data.model.StudentProfile
import com.example.ui.theme.AppThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

enum class AppDestination {
    SPLASH,
    ONBOARDING,
    ACTIVATION,
    ACTIVATION_SUCCESS,
    MAIN_APP,
    ATTENDANCE_MARKING,
    ATTENDANCE_SUCCESS
}

enum class MainTab {
    TODAY,
    ACTIVITY,
    HOSTEL,
    ME
}

class MainViewModel(
    val repository: HostelRepository = HostelRepository()
) : ViewModel() {

    private val _currentDestination = MutableStateFlow(AppDestination.SPLASH)
    val currentDestination: StateFlow<AppDestination> = _currentDestination.asStateFlow()

    private val _currentTab = MutableStateFlow(MainTab.TODAY)
    val currentTab: StateFlow<MainTab> = _currentTab.asStateFlow()

    // Activation input state (6-box PIN)
    private val _activationCode = MutableStateFlow("")
    val activationCode: StateFlow<String> = _activationCode.asStateFlow()

    private val _activationError = MutableStateFlow<String?>(null)
    val activationError: StateFlow<String?> = _activationError.asStateFlow()

    // Sheet states
    private val _showMessMenuSheet = MutableStateFlow(false)
    val showMessMenuSheet: StateFlow<Boolean> = _showMessMenuSheet.asStateFlow()

    private val _showNoticesSheet = MutableStateFlow(false)
    val showNoticesSheet: StateFlow<Boolean> = _showNoticesSheet.asStateFlow()

    private val _showAccountDetailsDialog = MutableStateFlow(false)
    val showAccountDetailsDialog: StateFlow<Boolean> = _showAccountDetailsDialog.asStateFlow()

    private val _showThemeDialog = MutableStateFlow(false)
    val showThemeDialog: StateFlow<Boolean> = _showThemeDialog.asStateFlow()

    // Activity tab sub-filter
    private val _activitySubTab = MutableStateFlow(0) // 0: Attendance, 1: Notices
    val activitySubTab: StateFlow<Int> = _activitySubTab.asStateFlow()

    // Hostel tab sub-filter
    private val _hostelSubTab = MutableStateFlow(0) // 0: Overview, 1: Notices
    val hostelSubTab: StateFlow<Int> = _hostelSubTab.asStateFlow()

    // Data streams from repository
    val studentProfile: StateFlow<StudentProfile> = repository.studentProfile
    val isAttendanceOpen: StateFlow<Boolean> = repository.isAttendanceOpen
    val isAttendanceMarked: StateFlow<Boolean> = repository.isAttendanceMarked
    val lastMarkedTime: StateFlow<String> = repository.lastMarkedTime
    val attendanceRecords: StateFlow<List<AttendanceRecord>> = repository.attendanceRecords
    val notices: StateFlow<List<Notice>> = repository.notices
    val messMeals: List<MessMeal> = repository.messMeals
    val themeMode: StateFlow<AppThemeMode> = repository.themeMode

    fun navigateTo(destination: AppDestination) {
        _currentDestination.value = destination
    }

    fun selectTab(tab: MainTab) {
        _currentTab.value = tab
    }

    fun setActivationCode(code: String) {
        if (code.length <= 6) {
            _activationCode.value = code.uppercase()
            _activationError.value = null
        }
    }

    fun submitActivation(): Boolean {
        val code = _activationCode.value
        if (code.length < 4) {
            _activationError.value = "Please enter the 6-digit code provided by your warden."
            return false
        }
        val success = repository.activateAccount(code)
        if (success) {
            _activationError.value = null
            navigateTo(AppDestination.ACTIVATION_SUCCESS)
            return true
        } else {
            _activationError.value = "Invalid activation code. Please check with warden."
            return false
        }
    }

    fun markAttendance() {
        repository.markAttendance()
        navigateTo(AppDestination.ATTENDANCE_SUCCESS)
    }

    fun toggleSessionOpen() {
        repository.toggleSessionOpen()
    }

    fun resetAttendanceForTesting() {
        repository.resetAttendanceForTesting()
    }

    fun setActivitySubTab(index: Int) {
        _activitySubTab.value = index
    }

    fun setHostelSubTab(index: Int) {
        _hostelSubTab.value = index
    }

    fun openMessMenu() {
        _showMessMenuSheet.value = true
    }

    fun closeMessMenu() {
        _showMessMenuSheet.value = false
    }

    fun openNotices() {
        _showNoticesSheet.value = true
    }

    fun closeNotices() {
        _showNoticesSheet.value = false
    }

    fun openAccountDetails() {
        _showAccountDetailsDialog.value = true
    }

    fun closeAccountDetails() {
        _showAccountDetailsDialog.value = false
    }

    fun openThemeDialog() {
        _showThemeDialog.value = true
    }

    fun closeThemeDialog() {
        _showThemeDialog.value = false
    }

    fun setThemeMode(mode: AppThemeMode) {
        repository.setThemeMode(mode)
    }

    fun signOut() {
        repository.signOut()
        _activationCode.value = ""
        navigateTo(AppDestination.ONBOARDING)
    }

    fun getStats(): AttendanceStats {
        return repository.getStats()
    }
}
