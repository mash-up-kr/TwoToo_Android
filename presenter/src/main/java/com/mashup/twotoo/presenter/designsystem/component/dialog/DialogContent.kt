package com.mashup.twotoo.presenter.designsystem.component.dialog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R

data class DialogContent(
    @StringRes val title: Int,
    @StringRes val desc: Int,
    @DrawableRes val image: Int?,
    val buttons: List<DialogButtonContent>,
) {
    companion object {
        val default = DialogContent(
            title = R.string.all_auth_title,
            desc = R.string.all_auth_desc,
            image = R.drawable.img_home_dialog_all_verified,
            buttons = emptyList(),
        )
        fun createHomeBothAuthDialogContent(
            negativeAction: () -> Unit,
            positiveAction: () -> Unit,
        ) = DialogContent(
            title = R.string.all_auth_title,
            desc = R.string.all_auth_desc,
            image = R.drawable.img_home_dialog_all_verified,
            buttons = listOf(
                DialogButtonContent(
                    text = R.string.cheer_up_cancel,
                    action = negativeAction,
                ),
                DialogButtonContent(
                    text = R.string.cheer_up,
                    action = positiveAction,
                ),
            ),
        )

        fun createHomeBloomBothDialogContent(
            onConfirm: () -> Unit,
        ) = DialogContent(
            title = R.string.congratulations,
            desc = R.string.congratulations_desc,
            image = R.drawable.img_home_dialog_all_bloom,
            buttons = listOf(
                DialogButtonContent(
                    text = R.string.button_confirm,
                    action = onConfirm,
                ),
            ),
        )

        fun createHomeDoNotBloomBothDialogContent(
            onConfirm: () -> Unit,
        ) = DialogContent(
            title = R.string.thankyou,
            desc = R.string.thankyou_desc,
            image = R.drawable.img_home_dialog_do_not_all_bloom,
            buttons = listOf(
                DialogButtonContent(
                    text = R.string.button_confirm,
                    action = onConfirm,
                ),
            ),
        )
    }
}
