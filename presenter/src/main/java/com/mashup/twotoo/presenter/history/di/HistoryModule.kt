package com.mashup.twotoo.presenter.history.di

import com.mashup.twotoo.presenter.history.HistoryViewModel
import dagger.Module
import dagger.Provides
import usecase.challenge.GetChallengeByNoUseCase
import usecase.challenge.QuiteChallengeUseCase
import usecase.commit.CreateCommitUseCase
import javax.inject.Scope

@Module
class HistoryModule {
    @Provides
    @HistoryScope
    fun provideViewModel(
        createCommitUseCase: CreateCommitUseCase,
        getChallengeByNoUseCase: GetChallengeByNoUseCase,
        quiteChallengeUseCase: QuiteChallengeUseCase,
    ): HistoryViewModel {
        return HistoryViewModel(
            createCommitUseCase = createCommitUseCase,
            getChallengeByNoUseCase = getChallengeByNoUseCase,
            quiteChallengeUseCase = quiteChallengeUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HistoryScope
