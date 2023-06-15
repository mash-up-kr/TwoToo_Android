package com.mashup.twotoo.presenter.history.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink

data class DropDialogTextUiModel(
    @StringRes val titleId: Int,
    val buttonAction: () -> Unit,
    val color: Color,
) {
    companion object {
        val default = listOf(
            DropDialogTextUiModel(
                titleId = R.string.challenge_done,
                {},
                TwotooPink,
            ),
            DropDialogTextUiModel(titleId = R.string.cancel, {}, Color.Black),
        )
    }
}
