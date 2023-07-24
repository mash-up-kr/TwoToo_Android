package com.mashup.twotoo.presenter.home.di

import com.mashup.twotoo.presenter.home.HomeViewModel
import dagger.Module
import dagger.Provides
import usecase.challenge.FinishChallengeWithNoUseCase
import usecase.commit.CreateCommitUseCase
import usecase.notification.StingUseCase
import usecase.user.GetVisibilityCheerDialogUseCase
import usecase.user.GetVisibilityCompleteDialogUseCase
import usecase.user.RemoveVisibilityCheerDialogUseCase
import usecase.user.RemoveVisibilityCompleteDialogUseCase
import usecase.user.SetVisibilityCheerDialogUseCase
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
        getVisibilityCheerDialogUseCase: GetVisibilityCheerDialogUseCase,
        getVisibilityCompleteDialogUseCase: GetVisibilityCompleteDialogUseCase,
        setVisibilityCheerDialogUseCase: SetVisibilityCheerDialogUseCase,
        setVisibilityCompleteDialogUseCase: SetVisibilityCompleteDialogUseCase,
        finishChallengeWithNoUseCase: FinishChallengeWithNoUseCase,
        removeVisibilityCheerDialogUseCase: RemoveVisibilityCheerDialogUseCase,
        removeVisibilityCompleteDialogUseCase: RemoveVisibilityCompleteDialogUseCase,
        stingUseCase: StingUseCase,
    ): HomeViewModel {
        return HomeViewModel(
            getHomeViewUseCase = getHomeViewChallengeStateUseCase,
            createCommitUseCase = createCommitUseCase,
            getVisibilityCheerDialogUseCase = getVisibilityCheerDialogUseCase,
            getVisibilityCompleteDialogUseCase = getVisibilityCompleteDialogUseCase,
            setVisibilityCheerDialogUseCase = setVisibilityCheerDialogUseCase,
            setVisibilityCompleteDialogUseCase = setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase = finishChallengeWithNoUseCase,
            removeVisibilityCheerDialogUseCase = removeVisibilityCheerDialogUseCase,
            removeVisibilityCompleteDialogUseCase = removeVisibilityCompleteDialogUseCase,
            stingUseCase = stingUseCase,
        )
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeScope
