package com.mashup.twotoo.presenter.invite.di

import com.mashup.twotoo.presenter.invite.InviteViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
class InviteModule {

    @Provides
    @InviteScope
    fun provideViewModel(): InviteViewModel {
        return InviteViewModel()
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class InviteScope
