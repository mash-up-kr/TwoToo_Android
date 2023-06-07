package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun Header(
    titleText: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = titleText,
        textAlign = TextAlign.Center,
        style = TwoTooTheme.typography.headLineNormal24,
        color = TwoTooTheme.color.mainBrown,
    )
}
