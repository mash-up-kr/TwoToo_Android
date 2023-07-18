package com.mashup.twotoo.presenter.invite.di

import com.mashup.twotoo.presenter.invite.InviteViewModel
import dagger.Module
import dagger.Provides
import usecase.user.GetPartnerInfoUseCase
import usecase.user.GetUserInfoUseCase
import javax.inject.Scope

@Module
class InviteModule {

    @Provides
    @InviteScope
    fun provideViewModel(
        getPartnerInfoUseCase: GetPartnerInfoUseCase,
        getUserInfoUseCase: GetUserInfoUseCase
    ): InviteViewModel {
        return InviteViewModel(getPartnerInfoUseCase, getUserInfoUseCase)
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class InviteScope
