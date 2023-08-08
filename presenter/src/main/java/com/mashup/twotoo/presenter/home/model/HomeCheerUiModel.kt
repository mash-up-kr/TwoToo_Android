package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/28
 */
data class HomeCheerUiModel(
    val cheerState: CheerState,
    val partner: CheerWithFlower,
    val me: CheerWithFlower,
) : ChallengeStateUiModel {
    companion object {
        val default = HomeCheerUiModel(
            cheerState = CheerState.DoNotCheerBoth,
            partner = CheerWithFlower.partnerNotYet,
            me = CheerWithFlower.meNotYet,
        )

        val cheerBoth = HomeCheerUiModel(
            cheerState = CheerState.CheerBoth,
            partner = CheerWithFlower.partnerNotEmpty,
            me = CheerWithFlower.meNotEmpty,
        )

        val doNotCheerBoth = HomeCheerUiModel(
            cheerState = CheerState.DoNotCheerBoth,
            partner = CheerWithFlower.partnerNotYet,
            me = CheerWithFlower.partnerNotYet,
        )

        val cheerOnlyMe = HomeCheerUiModel(
            cheerState = CheerState.CheerOnlyMe,
            partner = CheerWithFlower.partnerNotYet,
            me = CheerWithFlower.meNotEmpty,
        )

        val cheerOnlyPartner = HomeCheerUiModel(
            cheerState = CheerState.CheerOnlyPartner,
            partner = CheerWithFlower.partnerNotEmpty,
            me = CheerWithFlower.meNotYet,
        )
    }
}

data class CheerWithFlower(
    val homeFlowerUiModel: HomeFlowerUiModel,
    val cheerText: String = "",
    val commitNo: Int = -1,
) {
    companion object {
        val meNotYet = CheerWithFlower(
            homeFlowerUiModel = HomeFlowerUiModel.me,
            cheerText = "",
        )

        val meNotEmpty = CheerWithFlower(
            homeFlowerUiModel = HomeFlowerUiModel.me,
            cheerText = "나의응원임\n나의응원임",
        )

        val partnerNotYet = CheerWithFlower(
            homeFlowerUiModel = HomeFlowerUiModel.partner,
            cheerText = "",
        )

        val partnerNotEmpty = CheerWithFlower(
            homeFlowerUiModel = HomeFlowerUiModel.partner,
            cheerText = "파트너의응원입니다\n파트너의응원입니다",

        )
    }
}

enum class CheerState() {
    CheerOnlyMe, CheerOnlyPartner, CheerBoth, DoNotCheerBoth
}
