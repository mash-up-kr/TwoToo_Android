package com.mashup.twotoo.presenter.home.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R

/**
 * @Created by 김현국 2023/06/09
 */
enum class GrowType(@DrawableRes val growImage: Int, val width: Dp, val height: Dp) {
    SEED(growImage = R.drawable.ic_seed, width = 25.dp, height = 29.dp),
    SHOOT(growImage = R.drawable.ic_seed, width = 25.dp, height = 29.dp),
    BUD(growImage = R.drawable.ic_seed, width = 25.dp, height = 29.dp),
    FLOWER(growImage = R.drawable.ic_seed, width = 25.dp, height = 29.dp),
    BLOOM(growImage = R.drawable.ic_seed, width = 25.dp, height = 29.dp),
}
