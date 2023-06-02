package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R

sealed class BottomSheetType {
    @get:StringRes
    abstract val title: Int

    @get:StringRes
    abstract val textHint: Int
    data class Authenticate(
        override val title: Int = R.string.bottomSheetAuthenticate,
        override val textHint: Int = R.string.bottomSheetAuthTextFieldHint,
    ) : BottomSheetType()
    data class Shot(
        override val title: Int = R.string.bottomSheetShot,
        override val textHint: Int = R.string.bottomSheetShotTextFieldHint,
    ) : BottomSheetType()
    data class Cheer(
        override val title: Int = R.string.bottomSheetCheer,
        override val textHint: Int = R.string.bottomSheetCheerTextFieldHint,
    ) : BottomSheetType()
}
