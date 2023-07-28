package com.mashup.twotoo.presenter.createChallenge.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.model.FlowerName

data class FlowerCardUiModel(
    val flowerName: FlowerName,
    @StringRes val resourceName: Int,
    @StringRes val desc: Int,
    @DrawableRes val selectFlowerImage: Int,
    val index: Int
) {
    companion object {
        fun getFlowerCardModel(): List<FlowerCardUiModel> {
            return listOf<FlowerCardUiModel>(
                FlowerCardUiModel(
                    flowerName = FlowerName.Rose,
                    resourceName = R.string.rose,
                    desc = R.string.rose_language,
                    selectFlowerImage = R.drawable.img_challenge_select_rose,
                    0,
                ),
                FlowerCardUiModel(
                    flowerName = FlowerName.Tulip,
                    resourceName = R.string.tulip,
                    desc = R.string.tulip_language,
                    selectFlowerImage = R.drawable.img_challenge_select_tulip,
                    1,
                ),
                FlowerCardUiModel(
                    flowerName = FlowerName.Fig,
                    resourceName = R.string.fig,
                    desc = R.string.fig_language,
                    selectFlowerImage = R.drawable.img_challenge_select_fig,
                    2,
                ),
                FlowerCardUiModel(
                    flowerName = FlowerName.Cotton,
                    resourceName = R.string.cotton,
                    desc = R.string.cotton_language,
                    selectFlowerImage = R.drawable.img_challenge_select_cotton,
                    3,
                ),
                FlowerCardUiModel(
                    flowerName = FlowerName.Delphinium,
                    resourceName = R.string.delphinium,
                    desc = R.string.delphinium_language,
                    selectFlowerImage = R.drawable.img_challenge_select_delphinium,
                    4,
                ),
                FlowerCardUiModel(
                    flowerName = FlowerName.Camellia,
                    resourceName = R.string.camellia,
                    desc = R.string.camellia_language,
                    selectFlowerImage = R.drawable.img_challenge_select_camellia,
                    5,
                ),
                FlowerCardUiModel(
                    flowerName = FlowerName.Chrysanthemum,
                    resourceName = R.string.chrysanthemum,
                    desc = R.string.chrysanthemum_language,
                    selectFlowerImage = R.drawable.img_challenge_select_chrysanthemum,
                    6,
                ),
                FlowerCardUiModel(
                    flowerName = FlowerName.Sunflower,
                    resourceName = R.string.sunflower,
                    desc = R.string.sunflower_language,
                    selectFlowerImage = R.drawable.img_challenge_select_sunflower,
                    7,
                ),

            )
        }
    }
}
