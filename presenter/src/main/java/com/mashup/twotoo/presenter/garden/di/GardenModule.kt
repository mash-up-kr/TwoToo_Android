package com.mashup.twotoo.presenter.garden.di

import com.mashup.twotoo.presenter.garden.GardenViewModel
import dagger.Module
import dagger.Provides
import usecase.challenge.GetAllChallengeUseCase
import javax.inject.Scope

@Module
class GardenModule {
    @Provides
    @GardenScope
    fun provideViewModel(
        getAllChallengeUseCase: GetAllChallengeUseCase,
    ): GardenViewModel {
        return GardenViewModel(getAllChallengeUseCase = getAllChallengeUseCase)
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class GardenScope
