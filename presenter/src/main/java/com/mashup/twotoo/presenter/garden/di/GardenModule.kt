package com.mashup.twotoo.presenter.garden.di

import com.mashup.twotoo.presenter.garden.GardenViewModel
import dagger.Module
import dagger.Provides
import usecase.challenge.GetChallengeHistoriesUseCase
import javax.inject.Scope

@Module
class GardenModule {
    @Provides
    @GardenScope
    fun provideViewModel(
        getChallengeHistoriesUseCase: GetChallengeHistoriesUseCase,
    ): GardenViewModel {
        return GardenViewModel(getChallengeHistoriesUseCase = getChallengeHistoriesUseCase)
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class GardenScope
