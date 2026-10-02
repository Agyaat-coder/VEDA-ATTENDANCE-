package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.VedaAmber
import com.example.ui.theme.VedaBlueDark
import com.example.ui.theme.VedaBlueLight
import com.example.ui.theme.VedaBluePrimary
import com.example.ui.theme.VedaCyanAccent
import com.example.ui.theme.VedaGreen
import com.example.ui.theme.VedaNightBg
import com.example.ui.theme.VedaSky

/**
 * Winged VEDA Emblem Logo
 */
@Composable
fun VedaWingsLogo(
    modifier: Modifier = Modifier,
    size: Dp = 90.dp
) {
    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height

        // Outer glow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(VedaSky.copy(alpha = 0.25f), Color.Transparent),
                center = Offset(w * 0.5f, h * 0.5f),
                radius = w * 0.55f
            )
        )

        // Left wings (3 tiered feathers)
        // Lower feather
        val leftLower = Path().apply {
            moveTo(w * 0.5f, h * 0.72f)
            cubicTo(w * 0.44f, h * 0.65f, w * 0.32f, h * 0.52f, w * 0.16f, h * 0.42f)
            cubicTo(w * 0.20f, h * 0.50f, w * 0.28f, h * 0.62f, w * 0.44f, h * 0.70f)
            close()
        }
        drawPath(
            path = leftLower,
            brush = Brush.linearGradient(
                colors = listOf(VedaSky, VedaBluePrimary),
                start = Offset(w * 0.16f, h * 0.42f),
                end = Offset(w * 0.5f, h * 0.72f)
            )
        )

        // Middle feather
        val leftMid = Path().apply {
            moveTo(w * 0.5f, h * 0.64f)
            cubicTo(w * 0.42f, h * 0.54f, w * 0.28f, h * 0.38f, w * 0.14f, h * 0.28f)
            cubicTo(w * 0.18f, h * 0.38f, w * 0.28f, h * 0.50f, w * 0.44f, h * 0.62f)
            close()
        }
        drawPath(
            path = leftMid,
            brush = Brush.linearGradient(
                colors = listOf(VedaCyanAccent, VedaBluePrimary),
                start = Offset(w * 0.14f, h * 0.28f),
                end = Offset(w * 0.5f, h * 0.64f)
            )
        )

        // Top feather
        val leftTop = Path().apply {
            moveTo(w * 0.5f, h * 0.56f)
            cubicTo(w * 0.40f, h * 0.42f, w * 0.26f, h * 0.24f, w * 0.18f, h * 0.15f)
            cubicTo(w * 0.24f, h * 0.26f, w * 0.34f, h * 0.40f, w * 0.46f, h * 0.54f)
            close()
        }
        drawPath(
            path = leftTop,
            brush = Brush.linearGradient(
                colors = listOf(Color.White, VedaSky),
                start = Offset(w * 0.18f, h * 0.15f),
                end = Offset(w * 0.5f, h * 0.56f)
            )
        )

        // Right wings (symmetrical)
        val rightLower = Path().apply {
            moveTo(w * 0.5f, h * 0.72f)
            cubicTo(w * 0.56f, h * 0.65f, w * 0.68f, h * 0.52f, w * 0.84f, h * 0.42f)
            cubicTo(w * 0.80f, h * 0.50f, w * 0.72f, h * 0.62f, w * 0.56f, h * 0.70f)
            close()
        }
        drawPath(
            path = rightLower,
            brush = Brush.linearGradient(
                colors = listOf(VedaSky, VedaBluePrimary),
                start = Offset(w * 0.84f, h * 0.42f),
                end = Offset(w * 0.5f, h * 0.72f)
            )
        )

        val rightMid = Path().apply {
            moveTo(w * 0.5f, h * 0.64f)
            cubicTo(w * 0.58f, h * 0.54f, w * 0.72f, h * 0.38f, w * 0.86f, h * 0.28f)
            cubicTo(w * 0.82f, h * 0.38f, w * 0.72f, h * 0.50f, w * 0.56f, h * 0.62f)
            close()
        }
        drawPath(
            path = rightMid,
            brush = Brush.linearGradient(
                colors = listOf(VedaCyanAccent, VedaBluePrimary),
                start = Offset(w * 0.86f, h * 0.28f),
                end = Offset(w * 0.5f, h * 0.64f)
            )
        )

        val rightTop = Path().apply {
            moveTo(w * 0.5f, h * 0.56f)
            cubicTo(w * 0.60f, h * 0.42f, w * 0.74f, h * 0.24f, w * 0.82f, h * 0.15f)
            cubicTo(w * 0.76f, h * 0.26f, w * 0.66f, h * 0.40f, w * 0.54f, h * 0.54f)
            close()
        }
        drawPath(
            path = rightTop,
            brush = Brush.linearGradient(
                colors = listOf(Color.White, VedaSky),
                start = Offset(w * 0.82f, h * 0.15f),
                end = Offset(w * 0.5f, h * 0.56f)
            )
        )

        // Center bottom base
        val centerBase = Path().apply {
            moveTo(w * 0.5f, h * 0.78f)
            lineTo(w * 0.44f, h * 0.67f)
            lineTo(w * 0.56f, h * 0.67f)
            close()
        }
        drawPath(path = centerBase, color = Color(0xFFE0F2FE))

        // Center diamond
        val centerDiamond = Path().apply {
            moveTo(w * 0.5f, h * 0.48f)
            lineTo(w * 0.535f, h * 0.56f)
            lineTo(w * 0.5f, h * 0.64f)
            lineTo(w * 0.465f, h * 0.56f)
            close()
        }
        drawPath(path = centerDiamond, color = Color.White)
    }
}

/**
 * Atmospheric Hostel Night Building Graphic (Used on Splash Screen)
 */
@Composable
fun NightHostelSplashIllustration(
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        val w = size.width
        val h = size.height

        // Ground / lawn
        val groundGrad = Brush.verticalGradient(
            colors = listOf(Color(0xFF0F1A30), Color(0xFF070B18)),
            startY = h * 0.78f,
            endY = h
        )
        drawRect(brush = groundGrad, topLeft = Offset(0f, h * 0.78f), size = Size(w, h * 0.22f))

        // Pathways
        val path = Path().apply {
            moveTo(w * 0.42f, h * 0.80f)
            lineTo(w * 0.32f, h)
            lineTo(w * 0.68f, h)
            lineTo(w * 0.58f, h * 0.80f)
            close()
        }
        drawPath(path = path, color = Color(0xFF1E293B).copy(alpha = 0.5f))

        // Trees silhouettes
        drawCircle(color = Color(0xFF0A1528), radius = 34.dp.toPx(), center = Offset(w * 0.12f, h * 0.72f))
        drawCircle(color = Color(0xFF0D1B33), radius = 28.dp.toPx(), center = Offset(w * 0.18f, h * 0.74f))
        drawCircle(color = Color(0xFF0A1528), radius = 36.dp.toPx(), center = Offset(w * 0.88f, h * 0.72f))
        drawCircle(color = Color(0xFF0D1B33), radius = 30.dp.toPx(), center = Offset(w * 0.82f, h * 0.74f))

        // Center Hostel Building Main Block
        val buildingX = w * 0.22f
        val buildingW = w * 0.56f
        val buildingY = h * 0.28f
        val buildingH = h * 0.52f

        // Building shadow/silhouette
        drawRoundRect(
            color = Color(0xFF101B34),
            topLeft = Offset(buildingX, buildingY),
            size = Size(buildingW, buildingH),
            cornerRadius = CornerRadius(4.dp.toPx())
        )

        // Building roof pediment / parapet
        val pediment = Path().apply {
            moveTo(buildingX + buildingW * 0.25f, buildingY)
            lineTo(buildingX + buildingW * 0.5f, buildingY - 18.dp.toPx())
            lineTo(buildingX + buildingW * 0.75f, buildingY)
            close()
        }
        drawPath(path = pediment, color = Color(0xFF152244))

        // Left wing block
        drawRoundRect(
            color = Color(0xFF0D162C),
            topLeft = Offset(w * 0.08f, buildingY + 20.dp.toPx()),
            size = Size(buildingX - w * 0.08f, buildingH - 20.dp.toPx()),
            cornerRadius = CornerRadius(2.dp.toPx())
        )

        // Right wing block
        drawRoundRect(
            color = Color(0xFF0D162C),
            topLeft = Offset(buildingX + buildingW, buildingY + 20.dp.toPx()),
            size = Size(w * 0.92f - (buildingX + buildingW), buildingH - 20.dp.toPx()),
            cornerRadius = CornerRadius(2.dp.toPx())
        )

        // Windows with warm glowing yellow/amber lights
        val winW = 10.dp.toPx()
        val winH = 14.dp.toPx()
        val warmGlow = Color(0xFFFBBF24)
        val softGlow = Color(0xFFFEF3C7)
        val darkWin = Color(0xFF182645)

        // Floor windows (3 floors)
        val floors = listOf(buildingY + 26.dp.toPx(), buildingY + 54.dp.toPx(), buildingY + 82.dp.toPx())
        for ((fIdx, fY) in floors.withIndex()) {
            val cols = 6
            val step = buildingW / (cols + 1)
            for (c in 1..cols) {
                val winX = buildingX + (step * c) - (winW / 2)
                // Randomly light up some windows for realistic nocturnal hostel look
                val isLit = (fIdx + c) % 2 != 0 || (fIdx == 1 && c == 4) || (fIdx == 2 && c == 2)
                val cColor = if (isLit) {
                    if ((c + fIdx) % 3 == 0) softGlow else warmGlow
                } else darkWin

                drawRoundRect(
                    color = cColor,
                    topLeft = Offset(winX, fY),
                    size = Size(winW, winH),
                    cornerRadius = CornerRadius(2.dp.toPx())
                )

                // Window cross frame
                if (isLit) {
                    drawLine(
                        color = Color(0xFF451A03).copy(alpha = 0.4f),
                        start = Offset(winX + winW / 2, fY),
                        end = Offset(winX + winW / 2, fY + winH),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }
        }

        // Entrance Portal
        val entranceW = 26.dp.toPx()
        val entranceH = 34.dp.toPx()
        val entranceX = buildingX + (buildingW / 2) - (entranceW / 2)
        val entranceY = buildingY + buildingH - entranceH

        drawRoundRect(
            color = Color(0xFFFDE68A),
            topLeft = Offset(entranceX, entranceY),
            size = Size(entranceW, entranceH),
            cornerRadius = CornerRadius(12.dp.toPx(), 12.dp.toPx())
        )
        // Entrance door inner shadow
        drawRect(
            color = Color(0xFF451A03).copy(alpha = 0.3f),
            topLeft = Offset(entranceX + 2.dp.toPx(), entranceY + 6.dp.toPx()),
            size = Size(entranceW - 4.dp.toPx(), entranceH - 6.dp.toPx())
        )

        // Warm entrance lamp glow on ground
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFFFBBF24).copy(alpha = 0.45f), Color.Transparent),
                center = Offset(entranceX + entranceW / 2, entranceY + entranceH),
                radius = 45.dp.toPx()
            )
        )
    }
}

/**
 * Onboarding Student Walking to Campus Illustration
 */
@Composable
fun StudentOnboardingIllustration(
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp)
    ) {
        val w = size.width
        val h = size.height

        // Sky & soft gradient circle backdrop
        drawCircle(
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFFE0F2FE), Color(0xFFF0FDF4)),
                startY = 0f,
                endY = h * 0.85f
            ),
            radius = h * 0.48f,
            center = Offset(w * 0.5f, h * 0.48f)
        )

        // Campus building background in soft tones
        val bgBldgX = w * 0.44f
        val bgBldgW = w * 0.42f
        val bgBldgY = h * 0.22f
        val bgBldgH = h * 0.56f

        drawRoundRect(
            color = Color(0xFFCBD5E1),
            topLeft = Offset(bgBldgX, bgBldgY),
            size = Size(bgBldgW, bgBldgH),
            cornerRadius = CornerRadius(4.dp.toPx())
        )

        // Campus windows
        val winW = 8.dp.toPx()
        val winH = 12.dp.toPx()
        for (row in 0..2) {
            val yPos = bgBldgY + (16.dp.toPx() + row * 24.dp.toPx())
            for (col in 0..2) {
                val xPos = bgBldgX + 16.dp.toPx() + (col * 30.dp.toPx())
                drawRoundRect(
                    color = Color(0xFF94A3B8),
                    topLeft = Offset(xPos, yPos),
                    size = Size(winW, winH),
                    cornerRadius = CornerRadius(1.dp.toPx())
                )
            }
        }

        // Campus trees & green bushes
        drawCircle(color = Color(0xFF86EFAC), radius = 28.dp.toPx(), center = Offset(w * 0.38f, h * 0.68f))
        drawCircle(color = Color(0xFF4ADE80), radius = 22.dp.toPx(), center = Offset(w * 0.45f, h * 0.72f))

        // Ground lawn
        drawRoundRect(
            color = Color(0xFFE2E8F0),
            topLeft = Offset(w * 0.1f, h * 0.78f),
            size = Size(w * 0.8f, 16.dp.toPx()),
            cornerRadius = CornerRadius(8.dp.toPx())
        )

        // Student walking (Left foreground)
        val studentX = w * 0.32f

        // Head
        drawCircle(color = Color(0xFFFDBA74), radius = 13.dp.toPx(), center = Offset(studentX, h * 0.36f))
        // Hair
        drawCircle(color = Color(0xFF1E293B), radius = 13.5.dp.toPx(), center = Offset(studentX - 2.dp.toPx(), h * 0.34f))

        // Backpack on back
        drawRoundRect(
            color = Color(0xFF0284C7),
            topLeft = Offset(studentX - 18.dp.toPx(), h * 0.44f),
            size = Size(14.dp.toPx(), 26.dp.toPx()),
            cornerRadius = CornerRadius(6.dp.toPx())
        )

        // Shirt / Torso
        val torso = Path().apply {
            moveTo(studentX - 6.dp.toPx(), h * 0.44f)
            lineTo(studentX + 12.dp.toPx(), h * 0.44f)
            lineTo(studentX + 8.dp.toPx(), h * 0.64f)
            lineTo(studentX - 8.dp.toPx(), h * 0.64f)
            close()
        }
        drawPath(path = torso, color = Color(0xFF2563EB))

        // Legs walking
        // Front leg
        drawLine(
            color = Color(0xFF1E293B),
            start = Offset(studentX + 4.dp.toPx(), h * 0.64f),
            end = Offset(studentX + 14.dp.toPx(), h * 0.78f),
            strokeWidth = 6.dp.toPx(),
            cap = StrokeCap.Round
        )
        // Back leg
        drawLine(
            color = Color(0xFF334155),
            start = Offset(studentX - 4.dp.toPx(), h * 0.64f),
            end = Offset(studentX - 10.dp.toPx(), h * 0.78f),
            strokeWidth = 6.dp.toPx(),
            cap = StrokeCap.Round
        )

        // Sneakers
        drawRoundRect(
            color = Color.White,
            topLeft = Offset(studentX + 12.dp.toPx(), h * 0.77f),
            size = Size(10.dp.toPx(), 5.dp.toPx()),
            cornerRadius = CornerRadius(2.dp.toPx())
        )
    }
}

/**
 * Cozy Student Dorm Room Illustration (Home Empty State: "You're all caught up!")
 */
@Composable
fun CozyDormEmptyIllustration(
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        val w = size.width
        val h = size.height

        // Cozy room circle backdrop
        drawCircle(
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFFEFF6FF), Color(0xFFF8FAFC)),
                startY = 0f,
                endY = h
            ),
            radius = h * 0.48f,
            center = Offset(w * 0.5f, h * 0.5f)
        )

        // Dorm window with night/evening view
        val winX = w * 0.20f
        val winY = h * 0.12f
        val winW = w * 0.28f
        val winH = h * 0.48f

        // Window frame
        drawRoundRect(
            color = Color(0xFF0F172A),
            topLeft = Offset(winX, winY),
            size = Size(winW, winH),
            cornerRadius = CornerRadius(4.dp.toPx())
        )
        // Night sky in window
        drawRoundRect(
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFF0A1128), Color(0xFF1E3A8A)),
                startY = winY,
                endY = winY + winH
            ),
            topLeft = Offset(winX + 4.dp.toPx(), winY + 4.dp.toPx()),
            size = Size(winW - 8.dp.toPx(), winH - 8.dp.toPx()),
            cornerRadius = CornerRadius(2.dp.toPx())
        )
        // Crescent moon in window
        drawCircle(
            color = Color(0xFFFEF08A),
            radius = 6.dp.toPx(),
            center = Offset(winX + winW * 0.7f, winY + winH * 0.35f)
        )
        drawCircle(
            color = Color(0xFF0F1E3D),
            radius = 5.dp.toPx(),
            center = Offset(winX + winW * 0.67f, winY + winH * 0.33f)
        )

        // Study Desk
        val deskX = w * 0.16f
        val deskY = h * 0.62f
        val deskW = w * 0.68f
        val deskH = 10.dp.toPx()

        drawRoundRect(
            color = Color(0xFFB45309), // Warm wood desk
            topLeft = Offset(deskX, deskY),
            size = Size(deskW, deskH),
            cornerRadius = CornerRadius(3.dp.toPx())
        )
        // Desk legs
        drawLine(
            color = Color(0xFF78350F),
            start = Offset(deskX + 16.dp.toPx(), deskY + deskH),
            end = Offset(deskX + 16.dp.toPx(), h * 0.90f),
            strokeWidth = 6.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color(0xFF78350F),
            start = Offset(deskX + deskW - 16.dp.toPx(), deskY + deskH),
            end = Offset(deskX + deskW - 16.dp.toPx(), h * 0.90f),
            strokeWidth = 6.dp.toPx(),
            cap = StrokeCap.Round
        )

        // Laptop on desk
        val laptopX = w * 0.42f
        val laptopY = deskY - 26.dp.toPx()
        // Laptop screen
        drawRoundRect(
            color = Color(0xFF334155),
            topLeft = Offset(laptopX, laptopY),
            size = Size(36.dp.toPx(), 24.dp.toPx()),
            cornerRadius = CornerRadius(2.dp.toPx())
        )
        // Screen glow
        drawRect(
            color = Color(0xFF38BDF8),
            topLeft = Offset(laptopX + 2.dp.toPx(), laptopY + 2.dp.toPx()),
            size = Size(32.dp.toPx(), 20.dp.toPx())
        )
        // Laptop base
        drawRoundRect(
            color = Color(0xFF64748B),
            topLeft = Offset(laptopX - 4.dp.toPx(), deskY - 3.dp.toPx()),
            size = Size(44.dp.toPx(), 4.dp.toPx()),
            cornerRadius = CornerRadius(1.dp.toPx())
        )

        // Study Lamp (shining warm yellow light)
        val lampX = w * 0.68f
        val lampBaseY = deskY - 2.dp.toPx()

        // Lamp base
        drawCircle(color = Color(0xFF1E293B), radius = 6.dp.toPx(), center = Offset(lampX, lampBaseY))
        // Lamp neck
        val neck = Path().apply {
            moveTo(lampX, lampBaseY)
            lineTo(lampX - 6.dp.toPx(), lampBaseY - 28.dp.toPx())
            lineTo(lampX - 18.dp.toPx(), lampBaseY - 26.dp.toPx())
        }
        drawPath(path = neck, color = Color(0xFF1E293B), style = Stroke(width = 3.dp.toPx()))

        // Lamp shade
        val shade = Path().apply {
            moveTo(lampX - 26.dp.toPx(), lampBaseY - 24.dp.toPx())
            lineTo(lampX - 12.dp.toPx(), lampBaseY - 30.dp.toPx())
            lineTo(lampX - 10.dp.toPx(), lampBaseY - 22.dp.toPx())
            close()
        }
        drawPath(path = shade, color = Color(0xFFE11D48))

        // Lamp warm light cone
        val lightCone = Path().apply {
            moveTo(lampX - 22.dp.toPx(), lampBaseY - 20.dp.toPx())
            lineTo(lampX - 42.dp.toPx(), deskY)
            lineTo(lampX - 2.dp.toPx(), deskY)
            close()
        }
        drawPath(
            path = lightCone,
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFFFEF08A).copy(alpha = 0.55f), Color(0xFFFEF08A).copy(alpha = 0.05f)),
                startY = lampBaseY - 20.dp.toPx(),
                endY = deskY
            )
        )

        // Books stacked on left
        drawRoundRect(
            color = Color(0xFF059669),
            topLeft = Offset(deskX + 24.dp.toPx(), deskY - 7.dp.toPx()),
            size = Size(24.dp.toPx(), 6.dp.toPx()),
            cornerRadius = CornerRadius(1.dp.toPx())
        )
        drawRoundRect(
            color = Color(0xFF2563EB),
            topLeft = Offset(deskX + 26.dp.toPx(), deskY - 13.dp.toPx()),
            size = Size(22.dp.toPx(), 6.dp.toPx()),
            cornerRadius = CornerRadius(1.dp.toPx())
        )
    }
}

/**
 * Celebratory Check & Confetti Illustration (Used in Activation Success & Attendance Success)
 */
@Composable
fun CelebrationCheckmark(
    modifier: Modifier = Modifier,
    size: Dp = 100.dp
) {
    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height

        // Outer glow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(VedaGreen.copy(alpha = 0.25f), Color.Transparent),
                center = Offset(w * 0.5f, h * 0.5f),
                radius = w * 0.55f
            )
        )

        // Confetti particles around
        val confettiColors = listOf(
            Color(0xFF38BDF8), Color(0xFFF59E0B), Color(0xFFEC4899),
            Color(0xFF10B981), Color(0xFF6366F1), Color(0xFFFBBF24)
        )

        val confettiOffsets = listOf(
            Offset(w * 0.15f, h * 0.18f),
            Offset(w * 0.85f, h * 0.20f),
            Offset(w * 0.12f, h * 0.75f),
            Offset(w * 0.88f, h * 0.70f),
            Offset(w * 0.50f, h * 0.06f),
            Offset(w * 0.26f, h * 0.88f),
            Offset(w * 0.74f, h * 0.86f),
            Offset(w * 0.06f, h * 0.44f),
            Offset(w * 0.94f, h * 0.46f)
        )

        confettiOffsets.forEachIndexed { index, offset ->
            val color = confettiColors[index % confettiColors.size]
            if (index % 2 == 0) {
                drawCircle(color = color, radius = 3.dp.toPx(), center = offset)
            } else {
                drawRect(
                    color = color,
                    topLeft = Offset(offset.x - 3.dp.toPx(), offset.y - 2.dp.toPx()),
                    size = Size(6.dp.toPx(), 4.dp.toPx())
                )
            }
        }

        // Green Circle Badge
        val center = Offset(w * 0.5f, h * 0.5f)
        val circleRadius = w * 0.35f
        drawCircle(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFF34D399), Color(0xFF059669)),
                start = Offset(center.x - circleRadius, center.y - circleRadius),
                end = Offset(center.x + circleRadius, center.y + circleRadius)
            ),
            radius = circleRadius,
            center = center
        )

        // White Checkmark
        val checkPath = Path().apply {
            moveTo(w * 0.38f, h * 0.50f)
            lineTo(w * 0.47f, h * 0.60f)
            lineTo(w * 0.64f, h * 0.40f)
        }
        drawPath(
            path = checkPath,
            color = Color.White,
            style = Stroke(
                width = 5.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}

/**
 * Circular Attendance Percentage Gauge (for Activity Screen 88%)
 */
@Composable
fun CircularAttendanceGauge(
    percentage: Int = 88,
    modifier: Modifier = Modifier,
    size: Dp = 100.dp
) {
    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height
        val strokeWidth = 10.dp.toPx()
        val radius = (w - strokeWidth) / 2f
        val center = Offset(w / 2f, h / 2f)

        // Track background
        drawCircle(
            color = Color(0xFFE2E8F0),
            radius = radius,
            center = center,
            style = Stroke(width = strokeWidth)
        )

        // Progress Arc
        val sweepAngle = 360f * (percentage / 100f)
        drawArc(
            brush = Brush.sweepGradient(
                colors = listOf(Color(0xFF34D399), Color(0xFF059669), Color(0xFF10B981)),
                center = center
            ),
            startAngle = -90f,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = Offset(strokeWidth / 2f, strokeWidth / 2f),
            size = Size(w - strokeWidth, h - strokeWidth),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
    }
}
