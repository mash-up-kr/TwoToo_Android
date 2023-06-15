package com.mashup.twotoo.presenter.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun TwoTooNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color,
    content: @Composable RowScope.() -> Unit,
) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        NavigationBar(
            modifier = modifier,
            containerColor = containerColor,
            contentColor = TwoTooNavigationDefaults.navigationContentColor(),
            content = content,
        )
    }
}

@Composable
fun RowScope.TwoTooNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    contentColor: Color,
    unSelectedColor: Color,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = icon,
        modifier = modifier,
        label = label,
        alwaysShowLabel = false,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = TwoTooNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = unSelectedColor,
            indicatorColor = contentColor,
        ),
    )
}

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Transparent

    @Composable
    override fun rippleAlpha() = RippleAlpha(0F, 0F, 0F, 0F)
}

object TwoTooNavigationDefaults {
    @Composable
    fun navigationContentColor() = TwoTooTheme.color.backgroundYellow

    @Composable
    fun navigationSelectedItemColor() = TwoTooTheme.color.mainBrown
}
