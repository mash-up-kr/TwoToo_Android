package com.mashup.twotoo.presenter.di

import com.mashup.twotoo.presenter.createChallenge.di.CreateChallengeComponent
import com.mashup.twotoo.presenter.garden.di.GardenComponent
import com.mashup.twotoo.presenter.history.di.HistoryComponent
import com.mashup.twotoo.presenter.home.di.HomeComponent
import com.mashup.twotoo.presenter.invite.di.InviteComponent
import com.mashup.twotoo.presenter.mypage.di.UserComponent
import com.mashup.twotoo.presenter.nickname.di.NickNameSettingComponent
import com.mashup.twotoo.presenter.onboarding.di.OnboardingComponent
import com.mashup.twotoo.presenter.splash.di.SplashComponent
import dagger.Module

/**
 * @Created by 김현국 2023/06/23
 */

@Module(
    subcomponents =
    [
        SplashComponent::class,
        HomeComponent::class, UserComponent::class, HistoryComponent::class,
        GardenComponent::class,
        OnboardingComponent::class,
        NickNameSettingComponent::class,
        InviteComponent::class,
        CreateChallengeComponent::class,
    ],
)
class ScreenModule
