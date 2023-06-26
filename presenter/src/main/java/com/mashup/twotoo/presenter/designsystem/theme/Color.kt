package com.mashup.twotoo.presenter.designsystem.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val UnSelectedIconColor = Color(0xFF9E9E9E)
val SelectedIconColor = Color(0xFF000000)

// Todo 디자인 시스템에 등록해야하는지 디자인팀에게 질문해야하는 컬러들
val TwotooBlack = Color(0xFF000000)
val TwotooPink = Color(0xFFF07C4B)
val TwoTooRed = Color(0xFFFF3800)

// design system colors
val MainBrown = Color(0xFF443018)
val MainPink = Color(0xFFF3A989)
val MainLightPink = Color(0xFFF5DBD0)
val MainYellow = Color(0xFFFFE6AF)
val MainWhite = Color(0xFFFFFFFF)
val BackgroundYellow = Color(0xFFFCF5E6)
val Gray600 = Color(0xFF898784)
val Gray500 = Color(0xFFA09E9C)
val Gray400 = Color(0xFFD9D8D7)
val Gray300 = Color(0xFFE9E8E8)
val Gray200 = Color(0xFFF2F1F0)

@Stable
class ColorScheme(
    mainBrown: Color,
    mainPink: Color,
    mainYellow: Color,
    mainWhite: Color,
    backgroundYellow: Color,
    gray600: Color,
    gray500: Color,
    gray400: Color,
    gray300: Color,
    gray200: Color,
) {
    var mainBrown by mutableStateOf(mainBrown, structuralEqualityPolicy())
        internal set
    var mainPink by mutableStateOf(mainPink, structuralEqualityPolicy())
        internal set
    var mainYellow by mutableStateOf(mainYellow, structuralEqualityPolicy())
        internal set
    var mainWhite by mutableStateOf(mainWhite, structuralEqualityPolicy())
        internal set
    var backgroundYellow by mutableStateOf(backgroundYellow, structuralEqualityPolicy())
        internal set
    var gray600 by mutableStateOf(gray600, structuralEqualityPolicy())
        internal set
    var gray500 by mutableStateOf(gray500, structuralEqualityPolicy())
        internal set
    var gray400 by mutableStateOf(gray400, structuralEqualityPolicy())
        internal set
    var gray300 by mutableStateOf(gray300, structuralEqualityPolicy())
        internal set
    var gray200 by mutableStateOf(gray200, structuralEqualityPolicy())
        internal set

    fun copy(
        mainBrown: Color = this.mainBrown,
        mainPink: Color = this.mainPink,
        mainYellow: Color = this.mainYellow,
        mainWhite: Color = this.mainWhite,
        backgroundYellow: Color = this.backgroundYellow,
        gray600: Color = this.gray600,
        gray500: Color = this.gray500,
        gray400: Color = this.gray400,
        gray300: Color = this.gray300,
        gray200: Color = this.gray200,
    ): ColorScheme = ColorScheme(
        mainBrown,
        mainPink,
        mainYellow,
        mainWhite,
        backgroundYellow,
        gray600,
        gray500,
        gray400,
        gray300,
        gray200,
    )
}

/**
 * 지정된 ColorScheme의 내부 값을 다른 값으로 업데이트합니다.
 *
 *
 * 이렇게 하면 LocalTwoTooColor의 값을 사용하는 모든 컴포저블을 재구성하지 않고도
 * ColorScheme의 하위 집합을 효율적으로 업데이트할 수 있습니다.
 *
 *
 * ColorScheme는 LocalColorScheme에 새 값을 제공하면 LocalColoScheme를 사용하는,
 * 모든 컴포저블이 재구성됩니다. 이는 엄청나게 비용이 많이 듭니다.
 *
 *
 * 이 함수는 other의 값과 일치하도록 내부의 상태를 변경합니다.
 * 즉 모든 변경사항은 내부 상태를 변경하고 변경된 특정 값을 읽는 컴포저블만 재구성하도록 합니다.
 */
fun ColorScheme.updateColorSchemeFrom(other: ColorScheme) {
    mainBrown = other.mainBrown
    mainPink = other.mainPink
    mainYellow = other.mainYellow
    mainWhite = other.mainWhite
    backgroundYellow = other.backgroundYellow
    gray600 = other.gray600
    gray500 = other.gray500
    gray400 = other.gray400
    gray300 = other.gray300
    gray200 = other.gray200
}

enum class ThemeColor {
    Default,
}
fun getCurrentThemeColor(currentTheme: ThemeColor, isDarkTheme: Boolean): ColorScheme {
    return if (isDarkTheme) {
        // 다크테마일 경우
        lightColors()
    } else {
        // 다크 테마가 아닌, 앱에서 제공하는 다른 컬러 테마일 경우
        when (currentTheme) {
            ThemeColor.Default -> {
                lightColors()
            }
        }
    }
}
fun lightColors(
    mainBrown: Color = MainBrown,
    mainPink: Color = MainPink,
    mainYellow: Color = MainYellow,
    mainWhite: Color = MainWhite,
    backgroundYellow: Color = BackgroundYellow,
    gray600: Color = Gray600,
    gray500: Color = Gray500,
    gray400: Color = Gray400,
    gray300: Color = Gray300,
    gray200: Color = Gray200,
): ColorScheme = ColorScheme(
    mainBrown,
    mainPink,
    mainYellow,
    mainWhite,
    backgroundYellow,
    gray600,
    gray500,
    gray400,
    gray300,
    gray200,
)

val LocalTwoTooColor = staticCompositionLocalOf {
    lightColors()
}
