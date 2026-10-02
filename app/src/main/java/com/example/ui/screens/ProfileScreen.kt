package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.StudentProfile
import com.example.ui.theme.AppThemeMode
import com.example.ui.theme.VedaBluePrimary
import com.example.ui.theme.VedaCoral

@Composable
fun ProfileScreen(
    profile: StudentProfile,
    themeMode: AppThemeMode,
    onAccountClick: () -> Unit,
    onAppearanceClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onHelpClick: () -> Unit,
    onAboutClick: () -> Unit,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp)
            .testTag("profile_screen_root")
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Student Profile Header Card
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Student Avatar
            Box(
                modifier = Modifier
                    .size(86.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE2E8F0))
                    .border(2.dp, VedaBluePrimary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Student Portrait representation
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = profile.fullName,
                    tint = VedaBluePrimary,
                    modifier = Modifier.size(54.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = profile.fullName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Student ID: ${profile.studentId}",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "• ${profile.hostelName} • Room ${profile.roomNumber}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Settings Menu Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                ProfileMenuItem(
                    icon = Icons.Outlined.Person,
                    title = "Account",
                    subtitle = null,
                    onClick = onAccountClick,
                    testTag = "menu_account"
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.12f))

                ProfileMenuItem(
                    icon = Icons.Outlined.Palette,
                    title = "Appearance",
                    subtitle = when (themeMode) {
                        AppThemeMode.SYSTEM -> "System"
                        AppThemeMode.LIGHT -> "Light"
                        AppThemeMode.DARK -> "Dark"
                    },
                    onClick = onAppearanceClick,
                    testTag = "menu_appearance"
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.12f))

                ProfileMenuItem(
                    icon = Icons.Outlined.Notifications,
                    title = "Notifications",
                    subtitle = null,
                    onClick = onNotificationsClick,
                    testTag = "menu_notifications"
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.12f))

                ProfileMenuItem(
                    icon = Icons.Outlined.HelpOutline,
                    title = "Help & Feedback",
                    subtitle = null,
                    onClick = onHelpClick,
                    testTag = "menu_help"
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.12f))

                ProfileMenuItem(
                    icon = Icons.Outlined.Info,
                    title = "About VEDA",
                    subtitle = "Version 1.0.0",
                    onClick = onAboutClick,
                    testTag = "menu_about"
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Sign Out Button (Styled as prominent red rounded button like in mockup 10)
        Button(
            onClick = onSignOut,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("sign_out_button"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF87171),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Sign Out",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String?,
    onClick: () -> Unit,
    testTag: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .testTag(testTag),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.size(13.dp)
            )
        }
    }
}
