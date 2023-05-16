package com.mashup.twotoo.presenter.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.mashup.twotoo.presenter.R

object TwoTooIcon {
    val GardenSelected = R.drawable.garden_selected
    val GardenUnSelected = R.drawable.garden_unselected
    val HomeSelected = R.drawable.home_selected
    val HomeUnSelected = R.drawable.home_unselected
    val UserSelected = R.drawable.user_selected
    val UserUnSelected = R.drawable.user_unselected
}
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
