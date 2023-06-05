package com.mashup.twotoo.presenter.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.R

object Font {
    // Todo 제거 되어야합니다.
    val Omyuda = FontFamily(
        Font(R.font.omyuda_ippeum),
    )
    val Montserrat = FontFamily(
        Font(R.font.montserrat_medium, FontWeight.Bold),
    )
    val SpoqaHanSansNeo = FontFamily(
        Font(R.font.spoqa_hansans_neo_medium, FontWeight.Bold),

    )
}
val omyuda = FontFamily(
    Font(R.font.omyuda_font),
)

@Immutable
data class OmyudaTypography(
    val headLineNormal28: TextStyle,
    val headLineNormal24: TextStyle,
    val headLineNormal20: TextStyle,
    val headLineNormal18: TextStyle,
    val bodyNormal16: TextStyle,
    val bodyNormal14: TextStyle,
    val bodyNormal12: TextStyle,
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

val LocalTwoTooTypography = staticCompositionLocalOf {
    OmyudaTypography(
        headLineNormal28 = omyudaTextStyle.headLineNormal28,
        headLineNormal24 = omyudaTextStyle.headLineNormal24,
        headLineNormal20 = omyudaTextStyle.headLineNormal20,
        headLineNormal18 = omyudaTextStyle.headLineNormal18,
        bodyNormal16 = omyudaTextStyle.bodyNormal16,
        bodyNormal14 = omyudaTextStyle.bodyNormal14,
        bodyNormal12 = omyudaTextStyle.bodyNormal12,
    )
}
