package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import android.net.Uri
import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R

sealed interface BottomSheetType {

    @get:StringRes
    val title: Int

    @get:StringRes
    val textHint: Int

    data class Authenticate(
        override val title: Int = R.string.bottomSheetAuthenticate,
        override val textHint: Int = R.string.bottomSheetAuthTextFieldHint,
    ) : BottomSheetType

    sealed class SendType : BottomSheetType {
        data class Shot(
            override val title: Int = R.string.bottomSheetShot,
            override val textHint: Int = R.string.bottomSheetShotTextFieldHint,
        ) : SendType()

        data class Cheer(
            override val title: Int = R.string.bottomSheetCheer,
            override val textHint: Int = R.string.bottomSheetCheerTextFieldHint,
        ) : SendType()
    }
}

sealed interface BottomSheetData {

    val text: String
    data class AuthenticateData(
        val image: Uri,
        override val text: String,
    ) : BottomSheetData

    data class ShotData(
        override val text: String,
    ) : BottomSheetData

    data class CheerData(
        override val text: String,
    ) : BottomSheetData
}
