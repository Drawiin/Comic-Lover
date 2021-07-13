package com.example.comiclover.core.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.comiclover.R

val Marvel = FontFamily(
    Font(R.font.marvel_regular, FontWeight.Normal),
    Font(R.font.marvel_bold, FontWeight.Bold)
)
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Marvel,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = PrimaryTextColor
    ),
    h1 = TextStyle(
        fontFamily = Marvel,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        color = PrimaryTextColor
    ),
    h2 = TextStyle(
        fontFamily = Marvel,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = PrimaryTextColor
    ),
    h3 = TextStyle(
        fontFamily = Marvel,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = PrimaryTextColor
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)