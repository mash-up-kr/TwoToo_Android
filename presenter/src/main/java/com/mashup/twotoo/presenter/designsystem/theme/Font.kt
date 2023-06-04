package com.mashup.twotoo.presenter.designsystem.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.R

val omyuda = FontFamily(
    Font(R.font.omyuda_font),
)

data class OmyudaTypography(
    val headLineNormal28: TextStyle,
    val headLineNormal24: TextStyle,
    val headLineNormal20: TextStyle,
    val headLineNormal18: TextStyle,
    val bodyNormal16: TextStyle,
    val bodyNormal14: TextStyle,
    val bodyNormal12: TextStyle
)

val omyudaTextStyle = OmyudaTypography(
    headLineNormal28 = TextStyle(
        fontFamily = omyuda,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
    ),
    headLineNormal24 = TextStyle(
        fontFamily = omyuda,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),
    headLineNormal20 = TextStyle(
        fontFamily = omyuda,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    headLineNormal18 = TextStyle(
        fontFamily = omyuda,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    bodyNormal16 = TextStyle(
        fontFamily = omyuda,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodyNormal14 = TextStyle(
        fontFamily = omyuda,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    bodyNormal12 = TextStyle(
        fontFamily = omyuda,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
)
