package com.mashup.twotoo.presenter.history.model

import model.commit.response.CommitResponseDomainModel

data class HistoryItemUiModel(
    val partnerInfo: HistoryInfoUiModel,
    val myInfo: HistoryInfoUiModel,
    val createDate: String,
) {
    companion object {
        val default: List<HistoryItemUiModel> = listOf(
            HistoryItemUiModel(
                partnerInfo = HistoryInfoUiModel.default,
                myInfo = HistoryInfoUiModel.default,
                createDate = "4/10",
            ),
            HistoryItemUiModel(
                partnerInfo = HistoryInfoUiModel.default,
                myInfo = HistoryInfoUiModel.default,
                createDate = "4/9",
            ),
        )

        val empty: List<HistoryItemUiModel> = listOf(
            HistoryItemUiModel(
                partnerInfo = HistoryInfoUiModel.empty,
                myInfo = HistoryInfoUiModel.empty,
                createDate = "4/10",
            ),
        )

        // Todo creatDate 계산해야함
        fun from(commitResponseDomainModel: CommitResponseDomainModel): HistoryItemUiModel {
            return HistoryItemUiModel(
                partnerInfo = HistoryInfoUiModel.from(commitResponseDomainModel),
                myInfo = HistoryInfoUiModel.from(commitResponseDomainModel),
                createDate = "4/10",
            )
        }
    }
}
