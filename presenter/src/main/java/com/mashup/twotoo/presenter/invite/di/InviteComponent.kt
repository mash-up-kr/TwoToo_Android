package com.mashup.twotoo.presenter.invite.di

import com.mashup.twotoo.presenter.invite.InviteViewModel
import dagger.Subcomponent

@Subcomponent(modules = [InviteModule::class])
@InviteScope
interface InviteComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): InviteComponent
    }

    fun getViewModel(): InviteViewModel
}

interface InviteComponentProvider {
    fun provideInviteComponent(): InviteComponent
}
