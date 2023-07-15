package com.mashup.twotoo.presenter.nickname.di

import com.mashup.twotoo.presenter.nickname.NickNameViewModel
import dagger.Subcomponent

@Subcomponent(modules = [NickNameSettingModule::class])
@NickNameSettingScope
interface NickNameSettingComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NickNameSettingComponent
    }

    fun getViewModel(): NickNameViewModel
}

interface NickNameSettingComponentProvider {
    fun provideNickNameSettingComponent(): NickNameSettingComponent
}
