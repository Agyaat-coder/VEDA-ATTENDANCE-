package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.HostelRepository
import com.example.data.model.AttendanceStatus
import com.example.ui.AppDestination
import com.example.ui.MainTab
import com.example.ui.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `verify app name is VEDA`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("VEDA", appName)
    }

    @Test
    fun `verify account activation logic`() {
        val repo = HostelRepository()
        val viewModel = MainViewModel(repo)

        viewModel.setActivationCode("24A")
        assertFalse("Code less than 4 chars should fail", viewModel.submitActivation())

        viewModel.setActivationCode("24AIM1")
        assertTrue("Valid code should succeed", viewModel.submitActivation())
        assertEquals(AppDestination.ACTIVATION_SUCCESS, viewModel.currentDestination.value)
    }

    @Test
    fun `verify attendance marking records presence`() {
        val repo = HostelRepository()
        val viewModel = MainViewModel(repo)

        val initialStats = viewModel.getStats()
        viewModel.markAttendance()

        assertTrue(viewModel.isAttendanceMarked.value)
        assertEquals(AppDestination.ATTENDANCE_SUCCESS, viewModel.currentDestination.value)

        val updatedRecords = viewModel.attendanceRecords.value
        val latest = updatedRecords.first()
        assertEquals("Night Attendance", latest.title)
        assertEquals(AttendanceStatus.PRESENT, latest.status)
    }

    @Test
    fun `verify tab switching and session toggle`() {
        val repo = HostelRepository()
        val viewModel = MainViewModel(repo)

        viewModel.selectTab(MainTab.ACTIVITY)
        assertEquals(MainTab.ACTIVITY, viewModel.currentTab.value)

        viewModel.selectTab(MainTab.HOSTEL)
        assertEquals(MainTab.HOSTEL, viewModel.currentTab.value)

        val initialSessionState = viewModel.isAttendanceOpen.value
        viewModel.toggleSessionOpen()
        assertEquals(!initialSessionState, viewModel.isAttendanceOpen.value)
    }
}
