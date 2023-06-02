package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import androidx.annotation.StringRes
import kotlinx.collections.immutable.ImmutableList

sealed class BottomSheetType {
    @get:StringRes
    abstract val title: Int
    data class Authenticate(override val title: Int) : BottomSheetType()
    data class Shot(override val title: Int, val shotTextList: ImmutableList<String>) : BottomSheetType()
}
