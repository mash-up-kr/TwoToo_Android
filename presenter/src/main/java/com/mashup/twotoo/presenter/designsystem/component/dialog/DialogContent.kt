package com.mashup.twotoo.presenter.designsystem.component.dialog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R

class DialogContent private constructor(
    @StringRes val title: Int,
    @StringRes val desc: Int,
    val dialogImage: DialogImage,
    val buttons: List<DialogButtonContent>,
) {
    companion object {
        val default = DialogContent(
            title = R.string.all_auth_title,
            desc = R.string.all_auth_desc,
            dialogImage = DialogImage.bothAuth,
            buttons = emptyList(),
        )

        fun createHomeBothAuthDialogContent(
            negativeAction: () -> Unit,
            positiveAction: () -> Unit,
        ) = DialogContent(
            title = R.string.all_auth_title,
            desc = R.string.all_auth_desc,
            dialogImage = DialogImage.bothAuth,
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
            dialogImage = DialogImage.bloomBoth,
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
            dialogImage = DialogImage.doNotBloomBoth,
            buttons = listOf(
                DialogButtonContent(
                    text = R.string.button_confirm,
                    action = onConfirm,
                ),
            ),
        )
    }
}

data class DialogImage(
    @DrawableRes val image: Int?,
    val width: Dp,
    val height: Dp,
) {
    companion object {

        val bothAuth = DialogImage(
            image = R.drawable.img_home_dialog_all_verified,
            width = 123.dp,
            height = 87.dp,
        )

        val bloomBoth = DialogImage(
            image = R.drawable.img_home_dialog_all_bloom,
            width = 196.dp,
            height = 112.dp,
        )

        val doNotBloomBoth = DialogImage(
            image = R.drawable.img_home_dialog_do_not_all_bloom,
            width = 110.dp,
            height = 103.dp,
        )

        val leaveChallenge = DialogImage(
            image = R.drawable.crying_cloud,
            width = 113.dp,
            height = 96.dp,
        )
    }
}
