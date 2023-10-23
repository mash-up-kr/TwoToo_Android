package com.mashup.twotoo.presenter.home.model

import android.content.Context
import com.mashup.twotoo.presenter.model.FlowerName
import com.mashup.twotoo.presenter.model.Stage

data class FlowerLanguageUiModel(
    val challengeNo: Int = 0,
    val flowerName: FlowerName = FlowerName.Tulip
) {
    fun getFlowerLanguage(context: Context): String {
        val name = "${flowerName.name.lowercase()}_language"
        val flowerLanguage = context.resources.getIdentifier(name, "string", context.packageName)
        return context.getString(flowerLanguage)
    }

    fun getFlowerName(context: Context): String {
        val name = flowerName.name.lowercase()
        val flowerName = context.resources.getIdentifier(name, "string", context.packageName)
        return context.getString(flowerName)
    }

    fun getFlowerImage(context: Context): Int {
        val name = "img_home_${Stage.Fourth.name.lowercase()}_stage_${flowerName.name.lowercase()}_me"
        val image = context.resources.getIdentifier(name, "drawable", context.packageName)
        return image
    }
}
