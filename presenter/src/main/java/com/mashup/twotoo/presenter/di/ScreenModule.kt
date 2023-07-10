package com.mashup.twotoo.presenter.di

import com.mashup.twotoo.presenter.garden.di.GardenComponent
import com.mashup.twotoo.presenter.history.di.HistoryComponent
import com.mashup.twotoo.presenter.home.di.HomeComponent
import com.mashup.twotoo.presenter.mypage.di.UserComponent
import dagger.Module

/**
 * @Created by 김현국 2023/06/23
 */

@Module(subcomponents = [HomeComponent::class, UserComponent::class, HistoryComponent::class, GardenComponent::class])
class ScreenModule
