package com.mashup.twotoo.presenter.history

import com.mashup.twotoo.presenter.history.datail.model.HistoryDetailInfoUiModel
import com.mashup.twotoo.presenter.history.model.ChallengeInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel
import com.mashup.twotoo.presenter.history.model.OwnerNickNamesUiModel
import com.mashup.twotoo.presenter.home.mapper.toProgress
import com.mashup.twotoo.presenter.home.model.HomeGoalAchievePartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeGoalAchieveUiModel
import com.mashup.twotoo.presenter.home.model.UserType
import model.challenge.response.ChallengeDetailResponseDomainModel

data class HistoryState(
    val loadingIndicatorState: Boolean = false,
    val challengeInfoUiModel: ChallengeInfoUiModel = ChallengeInfoUiModel(),
    val historyItemUiModel: List<HistoryItemUiModel> = emptyList(),
    val ownerNickNamesUiModel: OwnerNickNamesUiModel = OwnerNickNamesUiModel(),
    val historyDetailInfoUiModel: HistoryDetailInfoUiModel = HistoryDetailInfoUiModel(),
    val homeGoalAchievePartnerAndMeUiModel: HomeGoalAchievePartnerAndMeUiModel? = null,
) {
    companion object {
        val default = HistoryState(
            challengeInfoUiModel = ChallengeInfoUiModel.default,
            historyItemUiModel = HistoryItemUiModel.default,
            ownerNickNamesUiModel = OwnerNickNamesUiModel.default,
            historyDetailInfoUiModel = HistoryDetailInfoUiModel.default,
        )

        fun ChallengeDetailResponseDomainModel.mapHomeGoalAchievePartnerAndMeUiModel(userNo: Int): HomeGoalAchievePartnerAndMeUiModel = with(this) {
            return if (userNo == this.challengeResponseDomainModel.user1.userNo) {
                HomeGoalAchievePartnerAndMeUiModel(
                    me = HomeGoalAchieveUiModel(
                        name = this.challengeResponseDomainModel.user1.nickname,
                        type = UserType.ME,
                        progress = this.myCommitResponseDomainModel.size.toProgress(),
                    ),
                    partner = HomeGoalAchieveUiModel(
                        name = this.challengeResponseDomainModel.user2.nickname,
                        type = UserType.PARTNER,
                        progress = this.partnerCommitResponseDomainModel.size.toProgress(),
                    ),
                )
            } else {
                HomeGoalAchievePartnerAndMeUiModel(
                    me = HomeGoalAchieveUiModel(
                        name = this.challengeResponseDomainModel.user2.nickname,
                        type = UserType.ME,
                        progress = this.partnerCommitResponseDomainModel.size.toProgress(),
                    ),
                    partner = HomeGoalAchieveUiModel(
                        name = this.challengeResponseDomainModel.user1.nickname,
                        type = UserType.PARTNER,
                        progress = this.myCommitResponseDomainModel.size.toProgress(),
                    ),
                )
            }
        }
    }
}
