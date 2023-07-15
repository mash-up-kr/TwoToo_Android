package com.mashup.twotoo.presenter.home.di

import com.mashup.twotoo.presenter.home.HomeViewModel
import dagger.Module
import dagger.Provides
import usecase.commit.CreateCommitUseCase
import usecase.view.GetViewHomeUseCase
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
        getHomeViewChallengeStateUseCase: GetViewHomeUseCase,
        createCommitUseCase: CreateCommitUseCase,
    ): HomeViewModel {
        return HomeViewModel(
            getHomeViewUseCase = getHomeViewChallengeStateUseCase,
            createCommitUseCase = createCommitUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeScope
