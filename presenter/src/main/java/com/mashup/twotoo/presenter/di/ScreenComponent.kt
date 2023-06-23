package com.mashup.twotoo.presenter.di

import com.mashup.twotoo.presenter.home.HomeComponent
import dagger.Module

/**
 * @Created by 김현국 2023/06/23
 */

@Module(subcomponents = [HomeComponent::class])
class ScreenComponent

interface ScreenComponentProvider {
    fun provideHomeComponent(): HomeComponent
}
