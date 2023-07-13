package com.mashup.twotoo.presenter.di

import com.mashup.twotoo.presenter.home.di.HomeComponent
import com.mashup.twotoo.presenter.invite.di.InviteComponent
import com.mashup.twotoo.presenter.mypage.di.UserComponent
import com.mashup.twotoo.presenter.nickname.di.NickNameSettingComponent
import com.mashup.twotoo.presenter.onboarding.di.OnboardingComponent
import dagger.Module

/**
 * @Created by 김현국 2023/06/23
 */

@Module(subcomponents = [HomeComponent::class, UserComponent::class, OnboardingComponent::class, NickNameSettingComponent::class, InviteComponent::class])
class ScreenModule
