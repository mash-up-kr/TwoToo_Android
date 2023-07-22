package com.mashup.twotoo.presenter.history.model

import com.mashup.twotoo.presenter.util.DateFormatter
import model.commit.response.CommitResponseDomainModel

data class HistoryItemUiModel(
    val myInfo: HistoryInfoUiModel,
    val partnerInfo: HistoryInfoUiModel,
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

        fun from(myCommit: CommitResponseDomainModel?, partnerCommit: CommitResponseDomainModel?): HistoryItemUiModel {
            return HistoryItemUiModel(
                myInfo = myCommit?.let {
                    HistoryInfoUiModel.from(it)
                } ?: HistoryInfoUiModel.empty,
                partnerInfo = partnerCommit?.let {
                    HistoryInfoUiModel.from(it)
                } ?: HistoryInfoUiModel.empty,
                createDate = myCommit?.let { DateFormatter.getDateStrByStr(it.createdAt) } ?: "",
            )
        }

        fun toSortDate(date: String): String {
            val (month, day) = date.split("-").run {
                (this[1].toInt() to this[2].toInt())
            }
            return "$month/$day"
        }
    }
}
