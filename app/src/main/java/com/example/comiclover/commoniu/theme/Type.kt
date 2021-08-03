package com.example.comiclover.commoniu.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = PrimaryBlack
    ),
    h1 = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 40.sp,
        color = PrimaryBlack
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp,
        color = PrimaryBlack
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        color = PrimaryBlack
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = PrimaryBlack
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
)