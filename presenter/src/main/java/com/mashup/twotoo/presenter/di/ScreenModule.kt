package com.mashup.twotoo.presenter.di

import com.mashup.twotoo.presenter.home.di.HomeComponent
import com.mashup.twotoo.presenter.mypage.di.UserComponent
import dagger.Module

/**
 * @Created by 김현국 2023/06/23
 */

@Module(subcomponents = [HomeComponent::class, UserComponent::class])
class ScreenModule
