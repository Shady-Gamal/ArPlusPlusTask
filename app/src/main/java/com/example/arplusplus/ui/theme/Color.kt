package com.example.arplusplus.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val gradientEnd = Color(0xFF1A00B8)
val gradientStart = Color(0xFF662A5A)
val selectedTab = Color(0xFFFFC700)
val linearGradient =
    Brush.linearGradient(
        0.0F to gradientStart,
        1.0F to gradientEnd,
        start = Offset.Zero,
        end = Offset.Infinite
    )
val primaryColor = Color(0xFF673db3)
val textColor = Color(0xFF6D6D6D)
val cardColor = Color(0xffFAF9F6)