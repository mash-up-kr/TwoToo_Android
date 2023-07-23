package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/23
 */

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.home.model.UserType.ME
import com.mashup.twotoo.presenter.home.model.UserType.PARTNER
import com.mashup.twotoo.presenter.model.FlowerImage
import com.mashup.twotoo.presenter.model.FlowerName
import com.mashup.twotoo.presenter.model.FlowerType
import com.mashup.twotoo.presenter.model.Stage
import com.mashup.twotoo.presenter.model.Stage.Fifth
import com.mashup.twotoo.presenter.model.Stage.First
import com.mashup.twotoo.presenter.model.Stage.Fourth
import com.mashup.twotoo.presenter.model.Stage.Second
import com.mashup.twotoo.presenter.model.Stage.Third
import com.mashup.twotoo.presenter.model.Stage.Zero

data class Flower(
    val flowerName: FlowerName,
    val userType: UserType,
    val growType: Stage,
) : FlowerType(
    name = flowerName,
) {
    override fun getFlowerImage(context: Context, screenWidth: Int, screenHeight: Int): FlowerImage {
        return when (userType) {
            ME -> {
                val name = "img_home_${growType.name.lowercase()}_stage_${flowerName.name.lowercase()}_me"
                val image = context.resources.getIdentifier(name, "drawable", context.packageName)
                val (width, height) = calculateWidthAndHeight(screenWidth, screenHeight, growType)
                when (growType) {
                    Zero -> FlowerImage(image = R.drawable.img_home_zero_stage_me, width = width, height = height)
                    First -> FlowerImage(image = R.drawable.img_home_first_stage_me, width = width, height = height)
                    Second -> FlowerImage(image = R.drawable.img_home_second_stage_me, width = width, height = height)
                    Third -> FlowerImage(image = R.drawable.img_home_third_stage_me, width = width, height = height)
                    Fourth -> FlowerImage(image = image, width = width, height = height)
                    Fifth -> FlowerImage(image = image, width = width, height = height)
                }
            }
            PARTNER -> {
                val name = "img_home_${growType.name.lowercase()}_stage_${flowerName.name.lowercase()}_partner"
                val image = context.resources.getIdentifier(name, "drawable", context.packageName)
                val (width, height) = calculateWidthAndHeight(screenWidth, screenHeight, growType)

                when (growType) {
                    Zero -> FlowerImage(image = R.drawable.img_home_zero_stage_partner, width = width, height = height)
                    First -> FlowerImage(image = R.drawable.img_home_first_stage_partner, width = width, height = height)
                    Second -> FlowerImage(image = R.drawable.img_home_second_stage_partner, width = width, height = height)
                    Third -> FlowerImage(image = R.drawable.img_home_third_stage_partner, width = width, height = height)
                    Fourth -> FlowerImage(image = image, width = width, height = height)
                    Fifth -> FlowerImage(image = image, width = width, height = height)
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

    private fun calculateWidthAndHeight(screenWidth: Int, screenHeight: Int, stage: Stage): Pair<Dp, Dp> {
        return when (stage) {
            Zero -> Pair((53 * screenWidth / figmaScreenWidth).dp, (49 * screenHeight / figmaScreenHeight).dp)
            First -> Pair((76 * screenWidth / figmaScreenWidth).dp, (69 * screenHeight / figmaScreenHeight).dp)
            Second -> Pair((77 * screenWidth / figmaScreenWidth).dp, (117 * screenHeight / figmaScreenHeight).dp)
            Third -> Pair((102 * screenWidth / figmaScreenWidth).dp, (183 * screenHeight / figmaScreenHeight).dp)
            Fourth -> Pair((127 * screenWidth / figmaScreenWidth).dp, (211 * screenHeight / figmaScreenHeight).dp)
            Fifth -> Pair((127 * screenWidth / figmaScreenWidth).dp, (211 * screenHeight / figmaScreenHeight).dp)
        }
    }

    companion object {
        const val figmaScreenHeight = 812
        const val figmaScreenWidth = 375
    }
}
