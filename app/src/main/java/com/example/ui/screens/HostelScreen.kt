package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Notice
import com.example.data.model.StudentProfile
import com.example.ui.theme.VedaBluePrimary

@Composable
fun HostelScreen(
    profile: StudentProfile,
    notices: List<Notice>,
    selectedSubTab: Int,
    onSubTabChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
            .testTag("hostel_screen_root")
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "Hostel",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Segmented Tabs: [ Overview | Notices ]
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                // Overview Tab
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                        .background(
                            color = if (selectedSubTab == 0) MaterialTheme.colorScheme.surface else Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { onSubTabChanged(0) }
                        .testTag("hostel_tab_overview"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Overview",
                        fontSize = 14.sp,
                        fontWeight = if (selectedSubTab == 0) FontWeight.Bold else FontWeight.Medium,
                        color = if (selectedSubTab == 0) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Notices Tab
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                        .background(
                            color = if (selectedSubTab == 1) MaterialTheme.colorScheme.surface else Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { onSubTabChanged(1) }
                        .testTag("hostel_tab_notices"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Notices",
                        fontSize = 14.sp,
                        fontWeight = if (selectedSubTab == 1) FontWeight.Bold else FontWeight.Medium,
                        color = if (selectedSubTab == 1) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        if (selectedSubTab == 0) {
            // OVERVIEW SUB-TAB
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Hostel Building Banner Card
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column {
                            // Beautiful Architectural Building Drawing
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(140.dp)
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(Color(0xFFE2E8F0), Color(0xFFCBD5E1))
                                        )
                                    )
                            ) {
                                CampusBuildingVector(
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = profile.hostelName,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.LocationOn,
                                        contentDescription = null,
                                        tint = VedaBluePrimary,
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = profile.university,
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }

                // Details List Card (Room 214, Floor 2, Warden Dr. S. Mishra)
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            HostelDetailRow(
                                icon = Icons.Filled.Bed,
                                label = "Room",
                                value = profile.roomNumber
                            )
                            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                            HostelDetailRow(
                                icon = Icons.Filled.Layers,
                                label = "Floor",
                                value = profile.floorNumber
                            )
                            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                            HostelDetailRow(
                                icon = Icons.Filled.Person,
                                label = "Warden",
                                value = profile.wardenName
                            )
                        }
                    }
                }

                // Important Contacts Header
                item {
                    Text(
                        text = "Important Contacts",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // Warden Office Contact
                item {
                    ContactCard(
                        title = "Warden Office",
                        phoneNumber = profile.wardenPhone,
                        icon = Icons.Filled.Person,
                        onCall = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${profile.wardenPhone}"))
                            context.startActivity(intent)
                        },
                        testTag = "call_warden_button"
                    )
                }

                // Security Gate Contact
                item {
                    ContactCard(
                        title = "Hostel Security Gate",
                        phoneNumber = profile.gatePhone,
                        icon = Icons.Filled.Security,
                        onCall = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${profile.gatePhone}"))
                            context.startActivity(intent)
                        },
                        testTag = "call_security_button"
                    )
                }

                // Medical / Emergency
                item {
                    ContactCard(
                        title = "Hostel Medical Desk",
                        phoneNumber = profile.emergencyPhone,
                        icon = Icons.Filled.Apartment,
                        onCall = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${profile.emergencyPhone}"))
                            context.startActivity(intent)
                        },
                        testTag = "call_medical_button"
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        } else {
            // NOTICES SUB-TAB
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(notices) { notice ->
                    NoticeItemCard(notice = notice)
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun HostelDetailRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
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
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun ContactCard(
    title: String,
    phoneNumber: String,
    icon: ImageVector,
    onCall: () -> Unit,
    testTag: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = VedaBluePrimary.copy(alpha = 0.12f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = VedaBluePrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = phoneNumber,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            IconButton(
                onClick = onCall,
                modifier = Modifier
                    .size(40.dp)
                    .testTag(testTag),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = VedaBluePrimary,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "Call $title",
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun CampusBuildingVector(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Sky & lawn
        drawRect(color = Color(0xFFE2E8F0), topLeft = Offset(0f, 0f), size = Size(w, h * 0.8f))
        drawRect(color = Color(0xFF86EFAC), topLeft = Offset(0f, h * 0.8f), size = Size(w, h * 0.2f))

        // Trees
        drawCircle(color = Color(0xFF22C55E), radius = 22.dp.toPx(), center = Offset(w * 0.12f, h * 0.76f))
        drawCircle(color = Color(0xFF16A34A), radius = 20.dp.toPx(), center = Offset(w * 0.88f, h * 0.76f))

        // University Hostel facade
        val bW = w * 0.65f
        val bH = h * 0.65f
        val bX = (w - bW) / 2f
        val bY = h * 0.15f

        drawRoundRect(
            color = Color(0xFF94A3B8),
            topLeft = Offset(bX, bY),
            size = Size(bW, bH),
            cornerRadius = CornerRadius(4.dp.toPx())
        )

        // Triangular roof pediment
        val roof = androidx.compose.ui.graphics.Path().apply {
            moveTo(bX + bW * 0.25f, bY)
            lineTo(bX + bW * 0.5f, bY - 14.dp.toPx())
            lineTo(bX + bW * 0.75f, bY)
            close()
        }
        drawPath(path = roof, color = Color(0xFF64748B))

        // Windows matrix
        val winW = 10.dp.toPx()
        val winH = 12.dp.toPx()
        for (r in 0..2) {
            val yPos = bY + 12.dp.toPx() + (r * 20.dp.toPx())
            for (c in 0..5) {
                val xPos = bX + 10.dp.toPx() + (c * 24.dp.toPx())
                drawRoundRect(
                    color = Color(0xFF38BDF8),
                    topLeft = Offset(xPos, yPos),
                    size = Size(winW, winH),
                    cornerRadius = CornerRadius(2.dp.toPx())
                )
            }
        }
    }
}
