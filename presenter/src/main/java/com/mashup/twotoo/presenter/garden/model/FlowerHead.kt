package com.mashup.twotoo.presenter.garden.model

import android.content.Context
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.home.model.flower.FlowerImage
import com.mashup.twotoo.presenter.home.model.flower.FlowerName
import com.mashup.twotoo.presenter.home.model.flower.FlowerType

data class FlowerHead(val flowerName: FlowerName) : FlowerType(
    name = flowerName,
) {
    override fun getFlowerImage(context: Context): FlowerImage {
        val name = "img_garden_${flowerName.name.lowercase()}"
        val image = context.resources.getIdentifier(name, "drawable", context.packageName)
        return FlowerImage(image = image, width = 127.dp, height = 211.dp)
    }
}
