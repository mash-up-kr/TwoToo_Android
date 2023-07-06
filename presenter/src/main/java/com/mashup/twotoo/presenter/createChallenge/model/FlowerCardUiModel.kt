package com.mashup.twotoo.presenter.createChallenge.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R

data class FlowerCardUiModel(
    @StringRes val name: Int,
    @StringRes val desc: Int,
    @DrawableRes val selectFlowerImage: Int,
) {
    companion object {
        fun getFlowerCardModel(): List<FlowerCardUiModel> {
            return listOf<FlowerCardUiModel>(
                FlowerCardUiModel(
                    name = R.string.rose,
                    desc = R.string.rose_language,
                    selectFlowerImage = R.drawable.img_challenge_select_rose,
                ),
                FlowerCardUiModel(
                    name = R.string.tulip,
                    desc = R.string.tulip_language,
                    selectFlowerImage = R.drawable.img_challenge_select_tulip,
                ),
                FlowerCardUiModel(
                    name = R.string.fig,
                    desc = R.string.fig,
                    selectFlowerImage = R.drawable.img_challenge_select_fig,
                ),
                FlowerCardUiModel(
                    name = R.string.cotton,
                    desc = R.string.cotton,
                    selectFlowerImage = R.drawable.img_challenge_select_cotton,
                ),
                FlowerCardUiModel(
                    name = R.string.delphinium,
                    desc = R.string.delphinium,
                    selectFlowerImage = R.drawable.img_challenge_select_delphinium,
                ),
                FlowerCardUiModel(
                    name = R.string.camellia,
                    desc = R.string.camellia,
                    selectFlowerImage = R.drawable.img_challenge_select_camellia,
                ),
                FlowerCardUiModel(
                    name = R.string.chrysanthemum,
                    desc = R.string.chrysanthemum,
                    selectFlowerImage = R.drawable.img_challenge_select_chrysanthemum,
                ),
                FlowerCardUiModel(
                    name = R.string.sunflower,
                    desc = R.string.sunflower,
                    selectFlowerImage = R.drawable.img_challenge_select_sunflower,
                ),

            )
        }
    }
}
