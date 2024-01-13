package com.mashup.twotoo.presenter.history.model

import com.mashup.twotoo.presenter.util.DateFormatter
import model.challenge.response.UserCommitResponseDomainModel
import model.commit.response.CommitResponseDomainModel

data class HistoryInfoUiModel(
    val commitNo: Int = 0,
    val userNo: Int,
    val text: String = "",
    val photoUrl: String = "",
    val createdDate: String = "",
    val createdTime: String = "",
    val partnerComment: String = "",
) {
    companion object {
        val default = HistoryInfoUiModel(
            photoUrl = "https://shop.biumfood.com/upload/1623296512image_product044.jpg",
            createdTime = "20:35",
            text = "오늘 따라 너무 피곤해서 힘들었다!!! 으악 ! \\n 그래도 귀여운 꽃을 키우기 위해 열심히 해야지 ㅎㅎ",
            userNo = 0,
        )
        val empty = HistoryInfoUiModel(
            photoUrl = "",
            createdTime = "",
            text = "",
            userNo = 0,
        )
    }
}
private fun String.toCreatedTime(): String {
    if (this.isEmpty()) {
        return this
    }
    return DateFormatter.get24HourStrByStr(this)
}

private fun String.toCreatedDate(): String {
    if (this.isEmpty()) {
        return this
    }
    val dateStr = DateFormatter.dateConvertToPlusNineTime(this, "yyyy-MM-dd")
    val (year, month, day) = dateStr.split("-")
    return "${year}년 ${month}월 ${day}일"
}

fun CommitResponseDomainModel.toHistoryDetailInfoUiModel(): HistoryInfoUiModel {
    return HistoryInfoUiModel(
        photoUrl = photoUrl,
        commitNo = commitNo,
        createdDate = createdAt.toCreatedDate(),
        createdTime = createdAt.toCreatedTime(),
        userNo = userNo,
        text = text,
        partnerComment = partnerComment,
    )
}
fun UserCommitResponseDomainModel.toHistoryDetailInfoUiModel(): HistoryInfoUiModel {
    return HistoryInfoUiModel(
        photoUrl = photoUrl,
        commitNo = commitNo,
        createdDate = createdAt.toCreatedDate(),
        createdTime = updatedAt.toCreatedDate(),
        userNo = userNo,
        text = text,
        partnerComment = partnerComment,
    )
}
