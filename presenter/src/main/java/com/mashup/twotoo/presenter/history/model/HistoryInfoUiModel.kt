package com.mashup.twotoo.presenter.history.model

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
    }
}
