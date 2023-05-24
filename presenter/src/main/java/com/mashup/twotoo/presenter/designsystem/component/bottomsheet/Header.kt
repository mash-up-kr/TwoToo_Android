package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Header(
    titleText: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = titleText,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
    )
}
