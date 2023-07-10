package com.mashup.twotoo.presenter.history.model

data class OwnerNickNamesUiModel(val parameterName: String = "", val myNickName: String = "") {

    companion object {
        val default = OwnerNickNamesUiModel("왕자", "공주")
    }
}
