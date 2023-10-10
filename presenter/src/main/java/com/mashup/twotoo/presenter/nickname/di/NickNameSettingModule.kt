package com.mashup.twotoo.presenter.nickname.di

import com.mashup.twotoo.presenter.nickname.NickNameViewModel
import dagger.Module
import dagger.Provides
import usecase.user.ChangeNicknameUseCase
import usecase.user.SetNickNameUseCase
import javax.inject.Scope

@Module
class NickNameSettingModule {

    @Provides
    @NickNameSettingScope
    fun provideViewModel(
        setNickNameUseCase: SetNickNameUseCase,
        changeNicknameUseCase: ChangeNicknameUseCase
    ): NickNameViewModel {
        return NickNameViewModel(
            setNickNameUseCase = setNickNameUseCase,
            changeNicknameUseCase = changeNicknameUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class NickNameSettingScope
