package com.mashup.twotoo.presenter.splash

import model.user.UserAuthResponseDomainModel

data class SplashModel(
    val userInfo: UserAuthResponseDomainModel = UserAuthResponseDomainModel()
)
