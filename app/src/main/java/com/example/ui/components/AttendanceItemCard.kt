package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness5
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AttendanceRecord
import com.example.data.model.AttendanceStatus
import com.example.data.model.SessionType
import com.example.ui.theme.VedaAmber
import com.example.ui.theme.VedaBluePrimary
import com.example.ui.theme.VedaSky

@Composable
fun AttendanceItemCard(
    record: AttendanceRecord,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Session Icon
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = when (record.sessionType) {
                                SessionType.NIGHT -> Color(0xFF1E293B)
                                SessionType.EVENING -> Color(0xFFFEF3C7)
                                SessionType.MORNING -> Color(0xFFE0F2FE)
                            },
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = when (record.sessionType) {
                            SessionType.NIGHT -> Icons.Filled.NightsStay
                            SessionType.EVENING -> Icons.Filled.WbSunny
                            SessionType.MORNING -> Icons.Filled.Brightness5
                        },
                        contentDescription = null,
                        tint = when (record.sessionType) {
                            SessionType.NIGHT -> VedaSky
                            SessionType.EVENING -> VedaAmber
                            SessionType.MORNING -> VedaBluePrimary
                        },
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = record.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${record.date} • ${record.timestamp}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Status Badge
            Surface(
                color = if (record.status == AttendanceStatus.PRESENT) Color(0xFFD1FAE5) else Color(0xFFFEE2E2),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = if (record.status == AttendanceStatus.PRESENT) "Present" else "Missed",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (record.status == AttendanceStatus.PRESENT) Color(0xFF047857) else Color(0xFFB91C1C),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
        }
    }
}
