package com.mashup.twotoo.presenter.invite.di

import androidx.lifecycle.SavedStateHandle
import com.mashup.twotoo.presenter.invite.InviteViewModel
import dagger.Module
import dagger.Provides
import usecase.user.GetUserNoUseCase
import javax.inject.Scope

@Module
class InviteModule {

    @Provides
    @InviteScope
    fun provideViewModel(getUserNoUseCase: GetUserNoUseCase): InviteViewModel {
        return InviteViewModel(getUserNoUseCase)
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class InviteScope
