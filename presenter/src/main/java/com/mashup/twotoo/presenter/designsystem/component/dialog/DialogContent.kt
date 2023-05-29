package com.mashup.twotoo.presenter.designsystem.component.dialog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DialogContent(
    @StringRes val title: Int,
    @StringRes val desc: Int,
    @DrawableRes val image: Int?,
    val buttons: List<DialogButtonContent>
)
