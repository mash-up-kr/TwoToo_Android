package com.mashup.twotoo.presenter.model

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

    // Todo Default enum 또는 서버에러 처리 UI 필요함
    companion object {
        fun findBy(value: String): FlowerName {
            return values().firstOrNull { it.value == value } ?: Tulip
        }
    }
}

abstract class FlowerType(val name: FlowerName) {
    abstract fun getFlowerImage(context: Context): FlowerImage
}

data class FlowerImage(
    @DrawableRes val image: Int,
    val width: Dp,
    val height: Dp,
)
