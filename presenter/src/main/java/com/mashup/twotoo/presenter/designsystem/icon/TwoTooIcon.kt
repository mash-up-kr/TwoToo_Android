package com.mashup.twotoo.presenter.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.mashup.twotoo.presenter.R

object TwoTooIcon {
    val GardenIcon = R.drawable.ic_garden
    val HomeIcon = R.drawable.ic_home
    val UserIcon = R.drawable.ic_user
}
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
