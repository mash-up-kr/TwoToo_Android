package com.mashup.twotoo.presenter.home.model.flower

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp

/**
 * @Created by 김현국 2023/06/09
 */

enum class Stage {
    Zero,
    First,
    Second,
    Third,
    Fourth,
    Fifth,
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
    abstract fun getFlowerImage(context: Context): FlowerImage
    abstract fun getFlowerLanguage(context: Context): String
    abstract fun getFlowerName(context: Context): String
}

data class FlowerImage(
    @DrawableRes val image: Int,
    val width: Dp,
    val height: Dp,
)
