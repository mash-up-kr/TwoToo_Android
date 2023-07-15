package com.mashup.twotoo.presenter.di

import com.mashup.twotoo.presenter.garden.di.GardenComponentProvider
import com.mashup.twotoo.presenter.history.di.HistoryComponentProvider
import com.mashup.twotoo.presenter.home.di.HomeComponentProvider
import com.mashup.twotoo.presenter.invite.di.InviteComponentProvider
import com.mashup.twotoo.presenter.mypage.di.UserComponentProvider
import com.mashup.twotoo.presenter.nickname.di.NickNameSettingComponentProvider
import com.mashup.twotoo.presenter.onboarding.di.OnboardingComponentProvider

/**
 * App Module에서 Component를 알기 위한 인터페이스를 정의합니다.
 */

interface ScreenComponentProvider :
    HomeComponentProvider,
    UserComponentProvider,
    OnboardingComponentProvider,
    NickNameSettingComponentProvider,
    InviteComponentProvider,
    HistoryComponentProvider,
    GardenComponentProvider
