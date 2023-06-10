package com.mashup.twotoo.presenter.designsystem.theme

/**
 * @Created by 김현국 2023/06/04
 */

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

// Todo 디자인 시스템 적용 필요한 rounds
val TwoTooRound10 = RoundedCornerShape(10.dp)
val TwoTooRound4 = RoundedCornerShape(4.dp)

@Immutable
data class Shapes(
    val extraSmall: CornerBasedShape = ShapeDefaults.ExtraSmall,
    val small: CornerBasedShape = ShapeDefaults.Small,
    val medium: CornerBasedShape = ShapeDefaults.Medium,
    val large: CornerBasedShape = ShapeDefaults.Large,
)

object ShapeDefaults {
    val ExtraSmall: CornerBasedShape = RoundedCornerShape(10.dp)
    val Small: CornerBasedShape = RoundedCornerShape(15.dp)
    val Medium: CornerBasedShape = RoundedCornerShape(20.dp)
    val Large: CornerBasedShape = RoundedCornerShape(22.dp)
}

val LocalTwoTooShape = staticCompositionLocalOf {
    Shapes()
}
