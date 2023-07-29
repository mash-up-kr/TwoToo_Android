package com.mashup.twotoo.presenter.history.di

import com.mashup.twotoo.presenter.history.HistoryViewModel
import dagger.Module
import dagger.Provides
import usecase.challenge.GetChallengeByNoUseCase
import usecase.challenge.QuiteChallengeUseCase
import usecase.commit.CreateCommitUseCase
import usecase.user.GetUserInfoUseCase
import javax.inject.Scope

@Module
class HistoryModule {
    @Provides
    @HistoryScope
    fun provideViewModel(
        getUserInfoUseCase: GetUserInfoUseCase,
        createCommitUseCase: CreateCommitUseCase,
        getChallengeByNoUseCase: GetChallengeByNoUseCase,
        quiteChallengeUseCase: QuiteChallengeUseCase,
    ): HistoryViewModel {
        return HistoryViewModel(
            getUserInfoUseCase = getUserInfoUseCase,
            createCommitUseCase = createCommitUseCase,
            getChallengeByNoUseCase = getChallengeByNoUseCase,
            quiteChallengeUseCase = quiteChallengeUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HistoryScope
