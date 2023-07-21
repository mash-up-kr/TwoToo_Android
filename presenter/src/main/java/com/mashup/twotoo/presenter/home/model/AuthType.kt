package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/09
 */
enum class AuthType {
    FirstCreateChallenge,
    FirstCreateChallengeButAuthOnlyPartner,
    AuthOnlyPartner,
    AuthOnlyMe,
    DoNotAuthBoth,
    AuthBoth,
}