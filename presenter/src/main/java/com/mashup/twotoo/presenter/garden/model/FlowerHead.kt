package com.mashup.twotoo.presenter.garden.model

import android.content.Context
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.model.FlowerImage
import com.mashup.twotoo.presenter.model.FlowerName
import com.mashup.twotoo.presenter.model.FlowerType

data class FlowerHead(val flowerName: FlowerName) : FlowerType(
    name = flowerName,
) {
    override fun getFlowerImage(context: Context, screenWidth: Int, screenHeight: Int): FlowerImage {
        val name = "img_head_${flowerName.name.lowercase()}_sm"
        val image = context.resources.getIdentifier(name, "drawable", context.packageName)
        return FlowerImage(image = image, width = 68.dp, height = 68.dp)
    }
}
