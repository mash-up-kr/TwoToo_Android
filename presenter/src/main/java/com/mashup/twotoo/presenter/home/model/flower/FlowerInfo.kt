package com.mashup.twotoo.presenter.home.model.flower

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp

/**
 * @Created by 김현국 2023/06/09
 */

enum class Stage {
    Seed,
    Shoot,
    Bud,
    Flower,
    Bloom,
}

enum class FlowerName {
    Tulip,
    Rose,
    Cotton,
    Fig,
    Chrysanthemum,
    Sunflower,
    Camellia,
    Delphinium,
}

sealed class FlowerType(val name: FlowerName) {
    abstract val flowerImage: FlowerImage
}

data class FlowerImage(
    @DrawableRes val image: Int,
    val width: Dp,
    val height: Dp,
)
