package org.monitor.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.monitor.app.R


private val fontFamily = FontFamily(Font(R.font.poppins_black))


val Type= Typography(
    displayLarge = TextStyle(
        fontSize = 57.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
        color = light_gray,
        fontFamily = fontFamily
    ),

    displayMedium = TextStyle(
        fontSize = 45.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
        color = light_gray,
        fontFamily = fontFamily
    ),

    displaySmall = TextStyle(
        fontSize = 36.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
        color = light_gray,
        fontFamily = fontFamily
    ),

    titleLarge = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp,
        color = light_gray,
        fontFamily = fontFamily
    ),

    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.15.sp,
        lineHeight = 24.sp,
        color = light_gray,
        fontFamily = fontFamily

        ),

    titleSmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.1.sp,
        lineHeight = 20.sp,
        color = light_gray,
        fontFamily = fontFamily
    ),

    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = light_gray,
        fontFamily = fontFamily

    ),

    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        color = light_gray,
        fontFamily = fontFamily
    ),

    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = light_gray,
        fontFamily = fontFamily
    ),

    headlineLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        color = light_gray,
        fontFamily = fontFamily
    ),

    headlineMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        color = light_gray,
        fontFamily = fontFamily
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        color = light_gray,
        fontFamily = fontFamily
    ),

    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = light_gray,
        fontFamily = fontFamily
    ),

    labelMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = light_gray,
        fontFamily = fontFamily
    ),

    labelSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = light_gray,
        fontFamily = fontFamily
    )
)