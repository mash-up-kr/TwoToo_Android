package com.mashup.twotoo.presenter.createChallenge.model

data class FlowerCardModel(
    val name: String,
    val desc: String
) {
    companion object {
        fun getFlowerCardModelToPreview(): ArrayList<FlowerCardModel> {
            val list = arrayListOf<FlowerCardModel>()
            for (i in 0..10) {
                list.add(
                    FlowerCardModel(
                        name = "장미",
                        desc = "행복한 사랑을 이루고 싶어요",
                    ),
                )
            }
            return list
        }
    }
}
