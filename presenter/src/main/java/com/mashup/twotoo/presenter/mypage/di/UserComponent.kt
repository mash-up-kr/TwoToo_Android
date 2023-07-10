package com.mashup.twotoo.presenter.mypage.di

import com.mashup.twotoo.presenter.mypage.navigation.UserViewModel
import dagger.Subcomponent

/**
 * @Created by 김현국 2023/06/27
 */

@Subcomponent(modules = [UserModule::class])
@UserScope
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    fun getViewModel(): UserViewModel
}

interface UserComponentProvider {
    fun provideUserComponent(): UserComponent
}
