package com.mashup.twotoo.presenter.history.di

import com.mashup.twotoo.presenter.history.HistoryViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
class HistoryModule {
    @Provides
    @HistoryScope
    fun provideViewModel(): HistoryViewModel {
        return HistoryViewModel()
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HistoryScope
