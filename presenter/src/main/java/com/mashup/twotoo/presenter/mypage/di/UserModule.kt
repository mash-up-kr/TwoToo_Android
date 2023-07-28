package com.mashup.twotoo.presenter.mypage.di

import com.mashup.twotoo.presenter.mypage.UserViewModel
import dagger.Module
import dagger.Provides
import usecase.user.GetUserInfoUseCase
import javax.inject.Scope

/**
 * @Created by 김현국 2023/06/27
 */

@Module
class UserModule {

    @Provides
    @UserScope
    fun provideUserViewModel(
        getUserInfoUseCase: GetUserInfoUseCase,
    ): UserViewModel {
        return UserViewModel(
            getUserInfoUseCase = getUserInfoUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope
