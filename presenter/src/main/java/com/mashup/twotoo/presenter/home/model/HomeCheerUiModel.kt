package com.mashup.twotoo.presenter.home.model

import com.mashup.twotoo.presenter.home.model.CheerState.Empty
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
            partner = CheerWithFlower.default,
            me = CheerWithFlower.default,
        )
    }
}

data class CheerWithFlower(
    val cheerState: CheerState,
    val homeFlowerUiModel: HomeFlowerUiModel,
) {
    companion object {
        val default = CheerWithFlower(
            cheerState = NotYet,
            homeFlowerUiModel = HomeFlowerUiModel.me,
        )

        val me = CheerWithFlower(
            cheerState = Empty,
            homeFlowerUiModel = HomeFlowerUiModel.me,
        )

        val partner = CheerWithFlower(
            cheerState = Empty,
            homeFlowerUiModel = HomeFlowerUiModel.partner,
        )
    }
}

enum class CheerState {
    NotYet, Empty, NotEmpty
}
