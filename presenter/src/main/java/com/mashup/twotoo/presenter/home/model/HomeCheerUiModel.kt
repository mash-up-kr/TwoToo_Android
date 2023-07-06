package com.mashup.twotoo.presenter.home.model

import com.mashup.twotoo.presenter.home.model.CheerState.NotEmpty
import com.mashup.twotoo.presenter.home.model.CheerState.NotYet
/**
 * @Created by 김현국 2023/06/28
 */
data class HomeCheerUiModel(
    val partner: CheerWithFlower,
    val me: CheerWithFlower,
) : ChallengeStateUiModel {
    companion object {
        val default = HomeCheerUiModel(
            partner = CheerWithFlower.partnerNotYet,
            me = CheerWithFlower.meNotYet,
        )

        val cheerBoth = HomeCheerUiModel(
            partner = CheerWithFlower.partnerNotEmpty,
            me = CheerWithFlower.meNotEmpty,
        )

        val doNotCheerBoth = HomeCheerUiModel(
            partner = CheerWithFlower.partnerNotYet,
            me = CheerWithFlower.partnerNotYet,
        )

        val cheerOnlyMe = HomeCheerUiModel(
            partner = CheerWithFlower.partnerNotYet,
            me = CheerWithFlower.meNotEmpty,
        )

        val cheerOnlyPartner = HomeCheerUiModel(
            partner = CheerWithFlower.partnerNotEmpty,
            me = CheerWithFlower.meNotYet,
        )
    }
}

data class CheerWithFlower(
    val cheerState: CheerState,
    val homeFlowerUiModel: HomeFlowerUiModel,
    val cheerText: String = "",
) {
    companion object {
        val default = CheerWithFlower(
            cheerState = NotYet,
            homeFlowerUiModel = HomeFlowerUiModel.me,
        )

        val meNotYet = CheerWithFlower(
            cheerState = NotYet,
            homeFlowerUiModel = HomeFlowerUiModel.me,
        )

        val meNotEmpty = CheerWithFlower(
            cheerState = NotEmpty,
            homeFlowerUiModel = HomeFlowerUiModel.me,
        )

        val partnerNotYet = CheerWithFlower(
            cheerState = NotYet,
            homeFlowerUiModel = HomeFlowerUiModel.partner,
        )

        val partnerNotEmpty = CheerWithFlower(
            cheerState = NotEmpty,
            homeFlowerUiModel = HomeFlowerUiModel.partner,
        )
    }
}

enum class CheerState() {
    NotYet, NotEmpty
}
