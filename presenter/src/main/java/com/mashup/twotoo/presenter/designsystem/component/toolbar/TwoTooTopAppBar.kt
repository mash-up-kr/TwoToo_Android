package com.mashup.twotoo.presenter.designsystem.component.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink

@Composable
fun TwoTooTopAppBar(
    modifier: Modifier = Modifier,
    titleEnabled: Boolean = false,
    title: String = stringResource(id = R.string.app_name),
    titleAlignment: Arrangement.Horizontal = Arrangement.Center,
    titleTextStyle: TextStyle = TwoTooTheme.typography.headLineNormal28,
    titleTextColor: Color = TwotooPink,
    navigationIcon: (@Composable () -> Unit)? = null,
    actionIcons: (@Composable RowScope.() -> Unit)? = null,
) {
    Box(
        modifier = modifier.fillMaxWidth().height(54.dp).statusBarsPadding(),
    ) {
        if (titleEnabled) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = titleAlignment,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (titleAlignment != Arrangement.Center) {
                    Spacer(
                        modifier = Modifier.width(26.dp),
                    )
                }
                Text(
                    text = title,
                    color = titleTextColor,
                    style = titleTextStyle,
                )
            }
        }
        if (navigationIcon != null) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                navigationIcon()
            }
        }
        if (actionIcons != null) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                actionIcons()
                Spacer(
                    modifier = Modifier.width(15.5.dp),
                )
            }
        }
    }
}
