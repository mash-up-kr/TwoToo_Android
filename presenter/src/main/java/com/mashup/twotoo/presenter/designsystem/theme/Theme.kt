package com.mashup.twotoo.presenter.designsystem.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember

@Composable
fun TwoTooTheme(
    currentThemeColor: ThemeColor = ThemeColor.Default,
    darkTheme: Boolean = isSystemInDarkTheme(),
    @DrawableRes backgroundImageId: Int? = null,
    content: @Composable () -> Unit,
) {
    val colorScheme = getCurrentThemeColor(currentThemeColor, darkTheme)

    CustomTheme(
        colorScheme = colorScheme,
        typography = TwoTooTheme.typography,
        shapes = TwoTooTheme.shape,
        content = {
//            TwoTooScreenContainer(backgroundImageId = backgroundImageId, screen = { content() })
            content()
        },
    )
}

@Composable
fun CustomTheme(
    colorScheme: ColorScheme,
    shapes: Shapes,
    typography: OmyudaTypography,
    content: @Composable () -> Unit,
) {
    val rememberedColorScheme = remember {
        colorScheme.copy()
    }.apply {
        updateColorSchemeFrom(colorScheme)
    }

    /**
     * 기본적으로 MaterialTheme에서 제공하는 코드를 동일하게 사용합니다.
     */
    val rippleIndication = rememberRipple()
    val selectionColors = rememberTextSelectionColors(rememberedColorScheme)
    CompositionLocalProvider(
        LocalTwoTooColor provides rememberedColorScheme,
        LocalIndication provides rippleIndication,
        LocalRippleTheme provides MaterialRippleTheme,
        LocalTwoTooShape provides shapes,
        LocalTextSelectionColors provides selectionColors,
        LocalTwoTooTypography provides typography,
    ) {
        /**
         * 이 함수는 모든 텍스트 구성요소는 명시적으로 스타일이 지정되지 않은 한 이 스타일로 지정됩니다.
         */
        ProvideTextStyle(value = typography.bodyNormal14, content)
    }
}

@Composable
fun rememberTextSelectionColors(colorScheme: ColorScheme): TextSelectionColors {
    val primaryColor = colorScheme.mainBrown
    return remember(primaryColor) {
        TextSelectionColors(
            handleColor = primaryColor,
            backgroundColor = primaryColor.copy(alpha = TextSelectionBackgroundOpacity),
        )
    }
}
const val TextSelectionBackgroundOpacity = 0.4f

@Immutable
private object MaterialRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = LocalContentColor.current

    @Composable
    override fun rippleAlpha() = DefaultRippleAlpha
}

private val DefaultRippleAlpha = RippleAlpha(
    pressedAlpha = StateTokens.PressedStateLayerOpacity,
    focusedAlpha = StateTokens.FocusStateLayerOpacity,
    draggedAlpha = StateTokens.DraggedStateLayerOpacity,
    hoveredAlpha = StateTokens.HoverStateLayerOpacity,
)

object StateTokens {
    const val DraggedStateLayerOpacity = 0.16f
    const val FocusStateLayerOpacity = 0.12f
    const val HoverStateLayerOpacity = 0.08f
    const val PressedStateLayerOpacity = 0.12f
}
object TwoTooTheme {
    val color: ColorScheme
        @Composable
        get() = LocalTwoTooColor.current

    val typography: OmyudaTypography
        @Composable
        get() = LocalTwoTooTypography.current

    val shape: Shapes
        @Composable
        get() = LocalTwoTooShape.current
}
