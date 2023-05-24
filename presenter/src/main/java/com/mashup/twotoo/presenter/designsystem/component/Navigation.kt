package com.mashup.twotoo.presenter.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mashup.twotoo.presenter.designsystem.theme.SelectedIconColor
import com.mashup.twotoo.presenter.designsystem.theme.UnSelectedIconColor

@Composable
fun TwoTooNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = TwoTooNavigationDefaults.navigationContentColor(),
        content = content,
    )

}

@Composable
fun RowScope.TwoTooNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = false,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = TwoTooNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = TwoTooNavigationDefaults.navigationContentColor(),
            indicatorColor = TwoTooNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

object TwoTooNavigationDefaults {
    @Composable
    fun navigationContentColor() = UnSelectedIconColor

    @Composable
    fun navigationSelectedItemColor() = SelectedIconColor

    @Composable
    fun navigationIndicatorColor() = Color.White
}
