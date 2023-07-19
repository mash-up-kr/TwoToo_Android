package com.mashup.twotoo.presenter.history.model

import model.commit.response.CommitResponseDomainModel

data class HistoryInfoUiModel(
    val photoUrl: String = "",
    val createdTime: String = "",
) {

    companion object {
        val default = HistoryInfoUiModel(
            photoUrl = "https://shop.biumfood.com/upload/1623296512image_product044.jpg",
            createdTime = "20:35",
        )
        val empty = HistoryInfoUiModel(
            photoUrl = "",
            createdTime = "",
        )

        // Todo crateTime을 domainmodel에 추가해야함(공통으로 사용가능한지 확인 후)
        fun from(commit: CommitResponseDomainModel): HistoryInfoUiModel {
            return HistoryInfoUiModel(
                photoUrl = commit.photoUrl,
                createdTime = "",
            )
        }
    }
}
