package com.mashup.twotoo.presenter.history.model

import model.challenge.response.UserResponseDomainModel

data class OwnerNickNamesUiModel(val partnerName: String = "", val myNickName: String = "") {

    companion object {
        val default = OwnerNickNamesUiModel("왕자", "공주")
        fun from(me: UserResponseDomainModel, partner: UserResponseDomainModel): OwnerNickNamesUiModel {
            return OwnerNickNamesUiModel(
                partnerName = partner.nickname,
                myNickName = me.nickname,
            )
        }
    }
}
