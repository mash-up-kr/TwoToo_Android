package com.mashup.twotoo.presenter.history.di

import com.mashup.twotoo.presenter.history.HistoryViewModel
import dagger.Module
import dagger.Provides
import usecase.challenge.GetChallengeByNoUseCase
import javax.inject.Scope

@Module
class HistoryModule {
    @Provides
    @HistoryScope
    fun provideViewModel(
        getChallengeByNoUseCase: GetChallengeByNoUseCase,
    ): HistoryViewModel {
        return HistoryViewModel(
            getChallengeByNoUseCase = getChallengeByNoUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HistoryScope
