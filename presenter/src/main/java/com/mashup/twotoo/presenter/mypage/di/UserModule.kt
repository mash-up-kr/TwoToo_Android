package com.mashup.twotoo.presenter.mypage.di

import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.mypage.navigation.UserViewModel
import dagger.Binds
import dagger.Module
import javax.inject.Scope

/**
 * @Created by 김현국 2023/06/27
 */

@Module
abstract class UserModule {

    @Binds
    @UserScope
    abstract fun provideUserViewModel(userViewModel: UserViewModel): ViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope
