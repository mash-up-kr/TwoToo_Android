package com.mashup.twotoo.presenter.home.di

import com.mashup.twotoo.presenter.home.HomeViewModel
import dagger.Module
import dagger.Provides
import usecase.challenge.GetHomeViewChallengeStateUseCase
import javax.inject.Scope

/**
 * @Created by 김현국 2023/06/27
 */

@Module
class HomeModule {

    // here is the simple code, but suppose that we provide something important here
    @Provides
    @HomeScope
    fun provideViewModel(
        getHomeViewChallengeStateUseCase: GetHomeViewChallengeStateUseCase,
    ): HomeViewModel {
        return HomeViewModel(
            getHomeViewChallengeStateUseCase = getHomeViewChallengeStateUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeScope
