package com.mashup.twotoo.presenter.history.datail.model

import com.mashup.twotoo.presenter.history.model.HistoryInfoUiModel

data class HistoryDetailInfoUiModel(
    val ownerNickName: String,
    val challengeName: String,
    val createdDate: String,
    val infoUiModel: HistoryInfoUiModel,
    val impressions: String,
    val partnerNickname: String,
    val complimentFromPartner: String = "",
) {
    companion object {
        val default: HistoryDetailInfoUiModel = HistoryDetailInfoUiModel(
            ownerNickName = "왕자",
            challengeName = "30분 이상 운동하기",
            createdDate = "2023년 4월 20일",
            infoUiModel = HistoryInfoUiModel.default,
            impressions = "오늘 따라 너무 피곤해서 힘들었다!!! 으악 ! \n 그래도 귀여운 꽃을 키우기 위해 열심히 해야지 ㅎㅎ",
            partnerNickname = "공주",
        )
    }
}
