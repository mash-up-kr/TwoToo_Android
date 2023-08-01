package com.mashup.twotoo.presenter.history.model

import com.mashup.twotoo.presenter.util.DateFormatter
import model.commit.response.CommitResponseDomainModel
import java.util.*

data class HistoryItemUiModel(
    val myInfo: HistoryInfoUiModel,
    val partnerInfo: HistoryInfoUiModel,
    val createDate: String,
) {
    val isAuthenticateExpired: Boolean = createDate != DateFormatter.getDateStrMonthDay(Date())
    companion object {
        val default: List<HistoryItemUiModel> = listOf(
            HistoryItemUiModel(
                partnerInfo = HistoryInfoUiModel.default,
                myInfo = HistoryInfoUiModel.default,
                createDate = "2023-04-10",
            ),
            HistoryItemUiModel(
                partnerInfo = HistoryInfoUiModel.default,
                myInfo = HistoryInfoUiModel.default,
                createDate = "2023-4-11",
            ),
        )

        val empty: List<HistoryItemUiModel> = listOf(
            HistoryItemUiModel(
                partnerInfo = HistoryInfoUiModel.empty,
                myInfo = HistoryInfoUiModel.empty,
                createDate = "2023-4-12",
            ),
        )

        fun from(myCommit: CommitResponseDomainModel?, partnerCommit: CommitResponseDomainModel?): HistoryItemUiModel {
            val commit = listOf(myCommit, partnerCommit).firstOrNull { it != null }
            val createAt = commit?.run {
                DateFormatter.dateConvertToPlusNineTime(createdAt)
            } ?: ""

            return HistoryItemUiModel(
                myInfo = myCommit?.let {
                    HistoryInfoUiModel.from(it)
                } ?: HistoryInfoUiModel.empty,
                partnerInfo = partnerCommit?.let {
                    HistoryInfoUiModel.from(it)
                } ?: HistoryInfoUiModel.empty,
                createDate = createAt,
            )
        }

        fun toSortDate(date: String): String {
            val (month, day) = date.split("-")
            return "$month/$day"
        }
    }
}
