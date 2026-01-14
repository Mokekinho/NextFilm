package com.example.nextfilm.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(

    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        letterSpacing = (-0.5).sp
    ),

    headlineMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),

    titleLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),

    bodyLarge = TextStyle(
        fontSize = 14.sp
    ),

    bodyMedium = TextStyle(
        fontSize = 12.sp,
        color = TextSecondary
    ),

    labelMedium = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium
    )
)