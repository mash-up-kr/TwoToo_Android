package com.mashup.twotoo.presenter.invite.di

import com.mashup.twotoo.presenter.invite.InviteViewModel
import dagger.Module
import dagger.Provides
import usecase.user.GetPartnerInfoUseCase
import usecase.user.GetUserInfoUseCase
import usecase.user.SetIsSendInvitationUseCase
import usecase.user.SetNickNameUseCase
import javax.inject.Scope

@Module
class InviteModule {

    @Provides
    @InviteScope
    fun provideViewModel(
        getPartnerInfoUseCase: GetPartnerInfoUseCase,
        getUserInfoUseCase: GetUserInfoUseCase,
        setIsSendInvitationUseCase: SetIsSendInvitationUseCase,
        setNickNameUseCase: SetNickNameUseCase
    ): InviteViewModel {
        return InviteViewModel(
            getPartnerInfoUseCase,
            getUserInfoUseCase,
            setIsSendInvitationUseCase,
            setNickNameUseCase
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class InviteScope
