package com.mashup.twotoo.presenter.di

import com.mashup.twotoo.presenter.home.HomeComponent
import dagger.Component
import dagger.Module

/**
 * @Created by 김현국 2023/06/23
 */

@Component(modules = [HomeComponent::class])
class ScreenComponent
