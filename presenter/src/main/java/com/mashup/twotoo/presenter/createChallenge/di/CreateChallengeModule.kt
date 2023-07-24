package com.mashup.twotoo.presenter.createChallenge.di

import com.mashup.twotoo.presenter.createChallenge.CreateChallengeViewModel
import dagger.Module
import dagger.Provides
import usecase.challenge.CreateChallengeUseCase
import javax.inject.Scope

@Module
class CreateChallengeModule {

    @Provides
    @CreateChallengeScope
    fun provideViewModel(
        createChallengeUseCase: CreateChallengeUseCase
    ): CreateChallengeViewModel {
        return CreateChallengeViewModel(
            createChallengeUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CreateChallengeScope
