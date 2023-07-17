package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/23
 */

import android.content.Context
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.home.model.UserType.ME
import com.mashup.twotoo.presenter.home.model.UserType.PARTNER
import com.mashup.twotoo.presenter.home.model.flower.FlowerImage
import com.mashup.twotoo.presenter.home.model.flower.FlowerName
import com.mashup.twotoo.presenter.home.model.flower.FlowerType
import com.mashup.twotoo.presenter.home.model.flower.Stage
import com.mashup.twotoo.presenter.home.model.flower.Stage.Fifth
import com.mashup.twotoo.presenter.home.model.flower.Stage.First
import com.mashup.twotoo.presenter.home.model.flower.Stage.Fourth
import com.mashup.twotoo.presenter.home.model.flower.Stage.Second
import com.mashup.twotoo.presenter.home.model.flower.Stage.Third
import com.mashup.twotoo.presenter.home.model.flower.Stage.Zero

data class Flower(
    val flowerName: FlowerName,
    val userType: UserType,
    val growType: Stage,
) : FlowerType(
    name = flowerName,
) {
    override fun getFlowerImage(context: Context): FlowerImage {
        return when (userType) {
            ME -> {
                val name = "img_home_${growType.name.lowercase()}_stage_${flowerName.name.lowercase()}_me"
                val image = context.resources.getIdentifier(name, "drawable", context.packageName)

                when (growType) {
                    Zero -> FlowerImage(image = R.drawable.img_home_zero_stage_me, width = 53.dp, height = 49.dp)
                    First -> FlowerImage(image = R.drawable.img_home_first_stage_me, width = 76.dp, height = 69.dp)
                    Second -> FlowerImage(image = R.drawable.img_home_second_stage_me, width = 77.dp, height = 117.dp)
                    Third -> FlowerImage(image = R.drawable.img_home_third_stage_me, width = 102.dp, height = 179.dp)
                    Fourth -> FlowerImage(image = image, width = 127.dp, height = 211.dp)
                    Fifth -> FlowerImage(image = image, width = 127.dp, height = 211.dp)
                }
            }
            PARTNER -> {
                val name = "img_home_${growType.name.lowercase()}_stage_${flowerName.name.lowercase()}_partner"
                val image = context.resources.getIdentifier(name, "drawable", context.packageName)

                when (growType) {
                    Zero -> FlowerImage(image = R.drawable.img_home_zero_stage_partner, width = 53.dp, height = 49.dp)
                    First -> FlowerImage(image = R.drawable.img_home_first_stage_partner, width = 61.dp, height = 69.dp)
                    Second -> FlowerImage(image = R.drawable.img_home_second_stage_partner, width = 77.dp, height = 117.dp)
                    Third -> FlowerImage(image = R.drawable.img_home_third_stage_partner, width = 102.dp, height = 179.dp)
                    Fourth -> FlowerImage(image = image, width = 127.dp, height = 211.dp)
                    Fifth -> FlowerImage(image = image, width = 127.dp, height = 211.dp)
                }
            }
        }
    }

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
}
