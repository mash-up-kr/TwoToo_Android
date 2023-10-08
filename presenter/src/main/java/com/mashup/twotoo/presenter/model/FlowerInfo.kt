package com.mashup.twotoo.presenter.model

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp
import com.mashup.twotoo.presenter.R

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

enum class FlowerName(val value: String) {
    Tulip("TULIP"),
    Rose("ROSE"),
    Cotton("COTTON"),
    Fig("FIG"),
    Chrysanthemum("CHRYSANTHEMUM"),
    Sunflower("SUNFLOWER"),
    Camellia("CAMELLIA"),
    Delphinium("DELPHINIUM"),
    ;

    companion object {
        fun findBy(value: String): FlowerName {
            return values().firstOrNull { it.value == value } ?: Tulip
        }
    }
}

fun String.toFlowerName(): FlowerName =
    FlowerName.findBy(this)

abstract class FlowerType(val name: FlowerName) {
    abstract fun getFlowerImage(context: Context, screenWidth: Int, screenHeight: Int): FlowerImage
}

data class FlowerImage(
    @DrawableRes val image: Int,
    val width: Dp,
    val height: Dp,
)

fun getFlowerLanguage(flowerName: FlowerName): Int {
    return when(flowerName) {
        FlowerName.Tulip -> R.string.tulip_language
        FlowerName.Rose -> R.string.rose_language
        FlowerName.Cotton -> R.string.cotton_language
        FlowerName.Fig -> R.string.fig_language
        FlowerName.Chrysanthemum -> R.string.chrysanthemum_language
        FlowerName.Sunflower -> R.string.sunflower_language
        FlowerName.Camellia -> R.string.camellia_language
        FlowerName.Delphinium -> R.string.delphinium_language
    }
}
