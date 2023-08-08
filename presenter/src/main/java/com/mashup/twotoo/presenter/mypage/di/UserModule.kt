package com.mashup.twotoo.presenter.mypage.di

import com.mashup.twotoo.presenter.mypage.UserViewModel
import dagger.Module
import dagger.Provides
import usecase.user.DeletePartnerUseCase
import usecase.user.GetUserInfoUseCase
import usecase.user.RemoveUserInfoUseCase
import usecase.user.SignOutUseCase
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
        deletePartnerUseCase: DeletePartnerUseCase,
        signOutUseCase: SignOutUseCase,
        removeUserInfoUseCase: RemoveUserInfoUseCase
    ): UserViewModel {
        return UserViewModel(
            getUserInfoUseCase = getUserInfoUseCase,
            deletePartnerUseCase = deletePartnerUseCase,
            signOutUseCase = signOutUseCase,
            removeUserInfoUseCase = removeUserInfoUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope
