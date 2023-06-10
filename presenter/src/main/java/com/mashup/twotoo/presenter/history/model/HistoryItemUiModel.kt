package com.mashup.twotoo.presenter.history.model

data class HistoryItemUiModel(
    val partnerInfo: HistoryInfoUiModel,
    val myInfo: HistoryInfoUiModel,
    val createDate: String,
) {
    companion object {
        fun generateDummyHistoryItemsToPreView(): List<HistoryItemUiModel> {
            return listOf(
                HistoryItemUiModel(
                    partnerInfo = HistoryInfoUiModel(
                        "https://shop.biumfood.com/upload/1623296512image_product044.jpg",
                        "20:35",
                    ),
                    myInfo = HistoryInfoUiModel(
                        "https://shop.biumfood.com/upload/1623296512image_product044.jpg",
                        "20:35",
                    ),
                    createDate = "4/10",
                ),
                HistoryItemUiModel(
                    partnerInfo = HistoryInfoUiModel(
                        "https://shop.biumfood.com/upload/1623296512image_product044.jpg",
                        "20:35",
                    ),
                    myInfo = HistoryInfoUiModel(
                        "https://shop.biumfood.com/upload/1623296512image_product044.jpg",
                        "20:35",
                    ),
                    createDate = "4/9",
                ),
            )
        }

        fun generateDummyEmptyHistoryItemsToPreView(): List<HistoryItemUiModel> {
            return listOf(
                HistoryItemUiModel(
                    partnerInfo = HistoryInfoUiModel(
                        "",
                        "",
                    ),
                    myInfo = HistoryInfoUiModel(
                        "",
                        "",
                    ),
                    createDate = "4/10",
                ),
            )
        }
    }
}
