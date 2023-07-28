package com.mashup.twotoo.presenter.createChallenge.di

import com.mashup.twotoo.presenter.createChallenge.CreateChallengeViewModel
import dagger.Subcomponent

@Subcomponent(modules = [CreateChallengeModule::class])
@CreateChallengeScope
interface CreateChallengeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CreateChallengeComponent
    }

    fun getViewModel(): CreateChallengeViewModel
}

interface CreateChallengeProvider {
    fun provideCreateChallengeComponent(): CreateChallengeComponent
}
