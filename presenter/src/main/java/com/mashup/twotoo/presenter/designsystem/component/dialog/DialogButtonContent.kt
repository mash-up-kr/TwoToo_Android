package com.mashup.twotoo.presenter.designsystem.component.dialog

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class DialogButtonContent(
    @StringRes val text: Int,
    val color: Color = Color.Black,
    val action: () -> Unit = {}
)
