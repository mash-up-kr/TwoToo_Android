package com.mashup.twotoo.presenter.home.di

import com.mashup.twotoo.presenter.home.HomeViewModel
import dagger.Module
import dagger.Provides
import usecase.challenge.FinishChallengeWithNoUseCase
import usecase.commit.CreateCheerUseCase
import usecase.commit.CreateCommitUseCase
import usecase.notification.StingUseCase
import usecase.user.GetVisibilityCompleteDialogUseCase
import usecase.user.RemoveVisibilityCompleteDialogUseCase
import usecase.user.SetVisibilityCompleteDialogUseCase
import usecase.view.GetViewHomeUseCase
import javax.inject.Scope

/**
 * @Created by 김현국 2023/06/27
 */

@Module
class HomeModule {

    // here is the simple code, but suppose that we provide something important here
    @Provides
    @HomeScope
    fun provideViewModel(
        getHomeViewChallengeStateUseCase: GetViewHomeUseCase,
        createCommitUseCase: CreateCommitUseCase,
        getVisibilityCompleteDialogUseCase: GetVisibilityCompleteDialogUseCase,
        setVisibilityCompleteDialogUseCase: SetVisibilityCompleteDialogUseCase,
        finishChallengeWithNoUseCase: FinishChallengeWithNoUseCase,
        removeVisibilityCompleteDialogUseCase: RemoveVisibilityCompleteDialogUseCase,
        createCheerUseCase: CreateCheerUseCase,
        stingUseCase: StingUseCase,
    ): HomeViewModel {
        return HomeViewModel(
            getHomeViewUseCase = getHomeViewChallengeStateUseCase,
            createCommitUseCase = createCommitUseCase,
            getVisibilityCompleteDialogUseCase = getVisibilityCompleteDialogUseCase,
            setVisibilityCompleteDialogUseCase = setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase = finishChallengeWithNoUseCase,
            removeVisibilityCompleteDialogUseCase = removeVisibilityCompleteDialogUseCase,
            createCheerUseCase = createCheerUseCase,
            stingUseCase = stingUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeScope
