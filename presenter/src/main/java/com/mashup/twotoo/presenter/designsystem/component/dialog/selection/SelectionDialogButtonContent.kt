package com.mashup.twotoo.presenter.designsystem.component.dialog.selection

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink

data class SelectionDialogButtonContent(
    @StringRes val titleId: Int,
    val buttonAction: () -> Unit,
    val color: Color,
) {
    companion object {
        val default = listOf(
            SelectionDialogButtonContent(
                titleId = R.string.challenge_done,
                {},
                TwotooPink,
            ),
            SelectionDialogButtonContent(titleId = R.string.cancel, {}, Color.Black),
        )
    }
}
