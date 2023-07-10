package com.mashup.twotoo.presenter.history.di

import com.mashup.twotoo.presenter.history.HistoryViewModel
import dagger.Subcomponent

@Subcomponent(modules = [HistoryModule::class])
@HistoryScope
interface HistoryComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HistoryComponent
    }

    fun getViewModel(): HistoryViewModel
}

interface HistoryComponentProvider {
    fun provideHistoryComponent(): HistoryComponent
}
