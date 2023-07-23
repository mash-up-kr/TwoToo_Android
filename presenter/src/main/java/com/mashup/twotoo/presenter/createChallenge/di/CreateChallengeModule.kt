package com.mashup.twotoo.presenter.createChallenge.di

import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import com.mashup.twotoo.presenter.createChallenge.CreateChallengeViewModel
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
