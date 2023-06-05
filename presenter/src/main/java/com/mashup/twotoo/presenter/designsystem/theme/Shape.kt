package com.mashup.twotoo.presenter.designsystem.theme

/**
 * @Created by 김현국 2023/06/04
 */

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class Shapes(
    val small: CornerBasedShape = ShapeDefaults.Small,
    val medium: CornerBasedShape = ShapeDefaults.Medium,
    val large: CornerBasedShape = ShapeDefaults.Large,
)

object ShapeDefaults {
    val Small: CornerBasedShape = RoundedCornerShape(10.dp)
    val Medium: CornerBasedShape = RoundedCornerShape(15.dp)
    val Large: CornerBasedShape = RoundedCornerShape(22.dp)
}

val LocalTwoTooShape = staticCompositionLocalOf {
    Shapes()
}
