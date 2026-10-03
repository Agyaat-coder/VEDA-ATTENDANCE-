package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.AppDestination
import com.example.ui.MainTab
import com.example.ui.MainViewModel
import com.example.ui.components.AccountDetailsDialog
import com.example.ui.components.MessMenuSheet
import com.example.ui.components.ThemeSelectorDialog
import com.example.ui.components.VedaBottomNav
import com.example.ui.screens.ActivationScreen
import com.example.ui.screens.ActivationSuccessScreen
import com.example.ui.screens.ActivityScreen
import com.example.ui.screens.AttendanceMarkingScreen
import com.example.ui.screens.AttendanceSuccessScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.HostelScreen
import com.example.ui.screens.OnboardingScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.theme.VedaTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val themeMode by viewModel.themeMode.collectAsState()

            VedaTheme(themeMode = themeMode) {
                val currentDestination by viewModel.currentDestination.collectAsState()
                val currentTab by viewModel.currentTab.collectAsState()
                val profile by viewModel.studentProfile.collectAsState()
                val isAttendanceOpen by viewModel.isAttendanceOpen.collectAsState()
                val isAttendanceMarked by viewModel.isAttendanceMarked.collectAsState()
                val lastMarkedTime by viewModel.lastMarkedTime.collectAsState()
                val attendanceRecords by viewModel.attendanceRecords.collectAsState()
                val notices by viewModel.notices.collectAsState()
                val activationCode by viewModel.activationCode.collectAsState()
                val activationError by viewModel.activationError.collectAsState()
                val activitySubTab by viewModel.activitySubTab.collectAsState()
                val hostelSubTab by viewModel.hostelSubTab.collectAsState()

                val showMessMenu by viewModel.showMessMenuSheet.collectAsState()
                val showAccountDetails by viewModel.showAccountDetailsDialog.collectAsState()
                val showThemeDialog by viewModel.showThemeDialog.collectAsState()

                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                // System Back Button Handling
                BackHandler(enabled = currentDestination != AppDestination.MAIN_APP || currentTab != MainTab.TODAY) {
                    when (currentDestination) {
                        AppDestination.ACTIVATION -> viewModel.navigateTo(AppDestination.ONBOARDING)
                        AppDestination.ACTIVATION_SUCCESS -> viewModel.navigateTo(AppDestination.MAIN_APP)
                        AppDestination.ATTENDANCE_MARKING -> viewModel.navigateTo(AppDestination.MAIN_APP)
                        AppDestination.ATTENDANCE_SUCCESS -> viewModel.navigateTo(AppDestination.MAIN_APP)
                        AppDestination.ONBOARDING -> finish()
                        AppDestination.MAIN_APP -> {
                            if (currentTab != MainTab.TODAY) {
                                viewModel.selectTab(MainTab.TODAY)
                            } else {
                                finish()
                            }
                        }
                        AppDestination.SPLASH -> finish()
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    bottomBar = {
                        if (currentDestination == AppDestination.MAIN_APP) {
                            VedaBottomNav(
                                selectedTab = currentTab,
                                onTabSelected = { viewModel.selectTab(it) }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                bottom = if (currentDestination == AppDestination.MAIN_APP) innerPadding.calculateBottomPadding() else 0.dp
                            )
                    ) {
                        when (currentDestination) {
                            AppDestination.SPLASH -> {
                                SplashScreen(
                                    onSplashFinished = {
                                        viewModel.navigateTo(AppDestination.ONBOARDING)
                                    }
                                )
                            }

                            AppDestination.ONBOARDING -> {
                                OnboardingScreen(
                                    onGetStarted = {
                                        viewModel.navigateTo(AppDestination.ACTIVATION)
                                    }
                                )
                            }

                            AppDestination.ACTIVATION -> {
                                ActivationScreen(
                                    code = activationCode,
                                    errorMessage = activationError,
                                    onCodeChange = { viewModel.setActivationCode(it) },
                                    onSubmit = {
                                        viewModel.submitActivation()
                                    },
                                    onBack = {
                                        viewModel.navigateTo(AppDestination.ONBOARDING)
                                    }
                                )
                            }

                            AppDestination.ACTIVATION_SUCCESS -> {
                                ActivationSuccessScreen(
                                    profile = profile,
                                    onContinue = {
                                        viewModel.navigateTo(AppDestination.MAIN_APP)
                                    }
                                )
                            }

                            AppDestination.ATTENDANCE_MARKING -> {
                                AttendanceMarkingScreen(
                                    profile = profile,
                                    onBack = {
                                        viewModel.navigateTo(AppDestination.MAIN_APP)
                                    },
                                    onMarkPresence = {
                                        viewModel.markAttendance()
                                    }
                                )
                            }

                            AppDestination.ATTENDANCE_SUCCESS -> {
                                AttendanceSuccessScreen(
                                    lastMarkedTime = lastMarkedTime,
                                    onViewInActivity = {
                                        viewModel.selectTab(MainTab.ACTIVITY)
                                        viewModel.setActivitySubTab(0)
                                        viewModel.navigateTo(AppDestination.MAIN_APP)
                                    },
                                    onBackToHome = {
                                        viewModel.selectTab(MainTab.TODAY)
                                        viewModel.navigateTo(AppDestination.MAIN_APP)
                                    }
                                )
                            }

                            AppDestination.MAIN_APP -> {
                                when (currentTab) {
                                    MainTab.TODAY -> {
                                        HomeScreen(
                                            profile = profile,
                                            isAttendanceOpen = isAttendanceOpen,
                                            isAttendanceMarked = isAttendanceMarked,
                                            unreadNoticesCount = notices.count { it.isNew },
                                            attendanceList = attendanceRecords,
                                            onMarkPresentClick = {
                                                viewModel.navigateTo(AppDestination.ATTENDANCE_MARKING)
                                            },
                                            onNoticesClick = {
                                                viewModel.selectTab(MainTab.ACTIVITY)
                                                viewModel.setActivitySubTab(1)
                                            },
                                            onMessMenuClick = {
                                                viewModel.openMessMenu()
                                            },
                                            onHostelInfoClick = {
                                                viewModel.selectTab(MainTab.HOSTEL)
                                                viewModel.setHostelSubTab(0)
                                            },
                                            onViewAllClick = {
                                                viewModel.selectTab(MainTab.ACTIVITY)
                                                viewModel.setActivitySubTab(0)
                                            }
                                        )
                                    }

                                    MainTab.ACTIVITY -> {
                                        ActivityScreen(
                                            stats = viewModel.getStats(),
                                            attendanceList = attendanceRecords,
                                            noticesList = notices,
                                            selectedSubTab = activitySubTab,
                                            onSubTabChanged = { viewModel.setActivitySubTab(it) }
                                        )
                                    }

                                    MainTab.HOSTEL -> {
                                        HostelScreen(
                                            profile = profile,
                                            notices = notices,
                                            selectedSubTab = hostelSubTab,
                                            onSubTabChanged = { viewModel.setHostelSubTab(it) }
                                        )
                                    }

                                    MainTab.ME -> {
                                        ProfileScreen(
                                            profile = profile,
                                            themeMode = themeMode,
                                            onAccountClick = { viewModel.openAccountDetails() },
                                            onAppearanceClick = { viewModel.openThemeDialog() },
                                            onNotificationsClick = {
                                                scope.launch {
                                                    snackbarHostState.showSnackbar("Attendance reminder notifications are Enabled")
                                                }
                                            },
                                            onHelpClick = {
                                                scope.launch {
                                                    snackbarHostState.showSnackbar("Hostel Grievance Desk: grievance.charak@vbspu.ac.in")
                                                }
                                            },
                                            onAboutClick = {
                                                scope.launch {
                                                    snackbarHostState.showSnackbar("VEDA Hostel v1.0.0 • Developed for Hostel Students")
                                                }
                                            },
                                            onSignOut = {
                                                viewModel.signOut()
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        // Sheets & Dialogs
                        if (showMessMenu) {
                            MessMenuSheet(
                                meals = viewModel.messMeals,
                                onDismiss = { viewModel.closeMessMenu() }
                            )
                        }

                        if (showAccountDetails) {
                            AccountDetailsDialog(
                                profile = profile,
                                onDismiss = { viewModel.closeAccountDetails() }
                            )
                        }

                        if (showThemeDialog) {
                            ThemeSelectorDialog(
                                currentTheme = themeMode,
                                onThemeSelect = { viewModel.setThemeMode(it) },
                                onDismiss = { viewModel.closeThemeDialog() }
                            )
                        }
                    }
                }
            }
        }
    }
}
