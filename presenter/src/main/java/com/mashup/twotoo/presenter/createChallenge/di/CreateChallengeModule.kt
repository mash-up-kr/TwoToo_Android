package com.mashup.twotoo.presenter.createChallenge.di

import com.mashup.twotoo.presenter.createChallenge.CreateChallengeViewModel
import dagger.Module
import dagger.Provides
import usecase.challenge.ApproveChallengeUseCase
import usecase.challenge.CreateChallengeUseCase
import javax.inject.Scope

@Module
class CreateChallengeModule {

    @Provides
    @CreateChallengeScope
    fun provideViewModel(
        approveChallengeUseCase: ApproveChallengeUseCase,
        createChallengeUseCase: CreateChallengeUseCase
    ): CreateChallengeViewModel {
        return CreateChallengeViewModel(
            approveChallengeUseCase,
            createChallengeUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CreateChallengeScope
