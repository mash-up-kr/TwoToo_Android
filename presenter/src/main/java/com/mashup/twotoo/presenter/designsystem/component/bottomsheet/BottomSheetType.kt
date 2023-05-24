package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R

enum class BottomSheetType(@StringRes val title: Int) {
    AUTHENTICATE(title = R.string.bottomSheetAuthenticate), SHOT(title = R.string.bottomSheetShot),
}
